package bomberman;

public class Launcher {

	public static void main(String[] args) {
		ConnectionHandler ch = new ConnectionHandler();
		Game game = new Game("Bomberman", ch, 750, 650);
		game.start();
	}
}
