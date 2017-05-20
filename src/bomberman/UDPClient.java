package bomberman;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UDPClient extends Thread {

	private int PORT;
	private int who;
	private Handler handler;

	public UDPClient(int PORT, int who, Handler handler) {
		this.PORT = PORT;
		this.who = who;
		this.handler = handler;
	}

	@Override
	public void run() {
		try {
			InetAddress address = InetAddress.getLocalHost();
			DatagramSocket datagramSocket = new DatagramSocket();

			float posX, posY;

			do {
				posX = handler.getPlayerPosX(who);
				posY = handler.getPlayerPosY(who);

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream daos = new DataOutputStream(baos);

				daos.writeFloat(posX);
				daos.writeFloat(posY);
				daos.writeInt(who);

				byte[] buffer = baos.toByteArray();

				DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
				datagramSocket.send(packet);

				/*try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
				
				byte[] buffer2 = new byte[50];
				packet = new DatagramPacket(buffer2, buffer2.length);
				datagramSocket.receive(packet);

				ByteArrayInputStream bais = new ByteArrayInputStream(buffer2);
				DataInputStream dais = new DataInputStream(bais);

				posX = dais.readFloat();
				posY = dais.readFloat();
				who = dais.readInt();

				handler.setPlayerPos(posX, posY, who);
				
				System.out.println("pos: " + who + " -> " + posX + " :: " + posY);

			} while (posX != -1.0f && posY != -1.0f);

			datagramSocket.close();
			System.out.println("Closing Client.");
		} catch (SocketTimeoutException e) {
			System.out.println("Socket timed out");
		} catch (IOException e1) {
			System.out.println("IOException: " + e1.getMessage());
		} catch (InputMismatchException e2) {
			System.out.println("Wrong Type! - " + e2.getMessage());
		}
	}
}
