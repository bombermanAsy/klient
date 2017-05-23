package bomberman;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	// private Lock lock = new ReentrantLock();
	private boolean ready = false;
	private int[] pl_num;
	public boolean canISend = false;
	private Dimension screenSize;
	private double screenWidth;
	private double screenHeight;

	public ConnectionHandler() {
		positions = new int[8];
		pl_num = new int[4];

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = screenSize.getWidth();
		screenHeight = screenSize.getHeight();

		try {
			socket = new Socket("127.0.0.1", PORT);
			// socket = new Socket("192.168.0.103", PORT);
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

	public int[] getPl_num() {
		return pl_num;
	}

	public void addHandler(Handler h) {
		this.handler = h;
		executor.submit(() -> listen());
	}

	/*
	 * public void startUDPClient() { new UDPClient(PORT, pl_num,
	 * handler).start(); System.out.println("UDP Client Started"); }
	 */

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
			waitingForPlayers = new JFrame("Waiting");
			waitingForPlayers.setLocation((int)screenWidth / 2 - 150, (int)screenHeight / 2 - 150);
			waitingForPlayers.setSize(310, 338);
			waitingForPlayers.setAlwaysOnTop(true);
			JLabel l = new JLabel(new ImageIcon("res/textures/Welcome.png"));
			l.setLocation(new Point(25, 0));
			waitingForPlayers.add(l);

			waitingForPlayers.setVisible(true);

			int x = (int) in.readObject();
			if (x != 0) {
				pl_num[0] = (int) in.readObject();
				positions[2 * pl_num[0]] = (int) in.readObject();
				positions[2 * pl_num[0] + 1] = (int) in.readObject();

				int m = 1;
				gracze = (int) in.readObject();

				for (int i = 0; i < gracze; i++) {
					int nr = (int) in.readObject();
					int a = (int) in.readObject();
					int b = (int) in.readObject();

					pl_num[m] = nr;
					positions[2 * pl_num[m]] = a;
					positions[2 * pl_num[m++] + 1] = b;

				}

				synchronized (executor) {
					ready = true;
					executor.notifyAll();
				}

			} else
				throw new Exception("Zbyt du¿a iloœæ pod³¹czonych graczy!");
			waitingForPlayers.setVisible(false);

			//////////////////////////////////////////////////////////////////////////////////
			// new UDPClient(PORT, pl_num, handler).start();

		} catch (Exception e) {
			System.out.println("B³¹d w makeConnection");
			e.printStackTrace();
		}
	}

	public synchronized void plantBomb(int x, int y) {
		try {
			out.writeObject('a');
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

			System.out.println("I am :: " + pl_num[0]);

			while (true) {

				char opt = (char) in.readObject();
				switch (opt) {
				case 'a':
					// postaw bombe
					int x = (int) in.readObject();
					int y = (int) in.readObject();
					handler.plantBomb(x, y, false);
					break;

				case 'c': // receive positions of player 'who' from server and
							// upload it
					int pos_x = (int) in.readObject();
					int pos_y = (int) in.readObject();
					int who = (int) in.readObject();
					// System.out.println("WHO: " + who + " / X: " + pos_x + " /
					// Y: " + pos_y);
					if (canISend == true) {
						handler.setPlayerPos(pos_x, pos_y, who);
					}
					break;

				}
				if (!alive)
					break;
			}

		} catch (Exception e) {
			try {
				in.close();
				out.close();
				socket.close();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
	}

	public synchronized void iDied() {
		try {
			out.writeObject('b');
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

	public synchronized void sendMyPos(float x, float y, int who) {
		try {
			out.writeObject('c');
			out.writeObject((int) x);
			out.writeObject((int) y);
			out.writeObject(who);
			// System.out.println("ConnectionHandler przesuwa gracza: " + who +
			// " na pozycje: " + x + ", " + y);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected void setScreenWidth(double screenWidth) {
		this.screenWidth = screenWidth;
	}

	protected double getScreenHeight() {
		return screenHeight;
	}

	protected void setScreenHeight(double screenHeight) {
		this.screenHeight = screenHeight;
	}
}
