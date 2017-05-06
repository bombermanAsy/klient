package bomberman;

import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ConnectionHandler {
	private static final int PORT = 1306;
	private Handler handler;
	private final ExecutorService executor = Executors.newFixedThreadPool(4);
	boolean alive = true;
	private Socket socket;
	private int[] positions; // kolejno X i Y - pierwsze moje, potem innych
	private int gracze;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Lock lock = new ReentrantLock();
	private boolean ready = false;

	public ConnectionHandler() {
		positions = new int[8];
		try {
			socket = new Socket("127.0.0.1", PORT);
			try {
				in = new ObjectInputStream(socket.getInputStream());
				out = new ObjectOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			makeConnection();
		} catch (Exception e) {
			System.out.println("Nie uda³o siê nawi¹zaæ po³¹czenia");
		}
	}

	public void addHandler(Handler h) {
		this.handler = h;
		executor.submit(() -> listen());
	}

	public int[] getPositions() {
		return positions;
	}

	public int getGracze() {
		return gracze + 1;
	}

	private void makeConnection() {
		try {
			System.out.println("Oczekiwanie na pozosta³ych graczy");
			JFrame waitingForPlayers;
			waitingForPlayers = new JFrame("OCZEKIWANIE NA GRACZY");
			waitingForPlayers.setLocation(150, 150);
			waitingForPlayers.setSize(350, 350);
			waitingForPlayers.setAlwaysOnTop(true);
			JLabel l = new JLabel(new ImageIcon("res/textures/Player1Anim.png"));
			l.setLocation(new Point(25, 0));
			waitingForPlayers.add(l);

			waitingForPlayers.setVisible(true);

			int x = (int) in.readObject();
			if (x != 0) {
				int y = (int) in.readObject();
				positions[0] = x;
				positions[1] = y;

				int k = 2;
				gracze = (int) in.readObject();

				for (int i = 0; i < gracze; i++) {
					int a = (int) in.readObject();
					int b = (int) in.readObject();

					positions[k++] = a;
					positions[k++] = b;
				}

				synchronized (executor) {
					ready = true;
					executor.notifyAll();
				}

			} else
				throw new Exception("Zbyt du¿a iloœæ pod³¹czonych graczy!");
			waitingForPlayers.setVisible(false);
		} catch (Exception e) {
			System.out.println("B³¹d w makeConnection");
			e.printStackTrace();
		}
	}

	public void plantBomb(int x, int y) {
		try {
			out.writeObject(1);
			out.writeObject(x);
			out.writeObject(y);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listen() {
		synchronized (executor) {
			while (!ready) {
				try {
					executor.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		try {
			while (true) {
				int opt = (int) in.readObject();
				switch (opt) {
				case 1:
					// postaw bombe
					int x = (int) in.readObject();
					int y = (int) in.readObject();
					handler.plantBomb(x, y, false);
					break;
				}
				if (!alive)
					break;
			}

		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public void iDied() {
		try {
			out.writeObject(2);
			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
