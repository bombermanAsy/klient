package bomberman;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionHandler {
	private static final int PORT = 1306;
	private Handler handler;
	private final ExecutorService executor = Executors.newFixedThreadPool(4);
	boolean alive = true;
	private Socket socket;
	
	public ConnectionHandler(Handler handler) {
	this.handler = handler;
	try {
		socket = new Socket("127.0.0.1", PORT); 
		sendMessage(1);
		} catch (Exception e) {
			System.out.println("Nie uda³o siê nawi¹zaæ po³¹czenia");
		}
	}
	
	public void sendMessage(int type) {
		switch(type) {
		case 1:
			// zalogowanie sie przez uzytkownika
			//executor.submit(this::makeConnection);
			makeConnection();
			break;
		case 2:
			// zawodnik postawil bombe
			//executor.submit(() -> this.plantBomb(x, y));
			break;
		case 3:
			// zawodnik zginal - w tej metodzie (iDied) trzeba zamknac socketa
			//executor.submit(() -> this.iDied());
			break;
		case 4:
			// wyslanie wsporzednych
			break;
		}
	}

	private void makeConnection() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());			
			out.writeObject(1);	
			
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			int x = (int) in.readObject();
			if (x != 0) {
				int y = (int) in.readObject();
				handler.addPlayer(x, y);
				
				while (in.available() != 1) {
				int gracze = (int) in.readObject();
        		for (int i=0; i < gracze; i++) {
        			int a = (int) in.readObject();
        			int b = (int) in.readObject();
        			handler.addPlayer(a, b);
        		}
				}
			}	
			else throw new Exception("Zbyt du¿a iloœæ pod³¹czonych graczy!");
			
			in.close();
			out.close();
		} catch (Exception e) {
		System.out.println("B³¹d w makeConnection");
		e.printStackTrace();
	}
}
	

}
