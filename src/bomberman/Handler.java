package bomberman;

import bomberman.entity.creatures.Player;
import bomberman.entity.items.Bomb;
import bomberman.input.KeyManager;
import bomberman.input.MouseManager;
import bomberman.worlds.World;

public class Handler {

	// private int[] myPos = new int[2];

	private Game game;
	private World world;

	public Handler(Game game) {
		this.game = game;
	}

	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}

	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}

	public int getWidth() {
		return game.getWidth();
	}

	public int getHeight() {
		return game.getHeight();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public void plantBomb(float x, float y, boolean mine) {
		this.world.plantBomb(x, y, mine);
	}

	public void explode(Bomb b) {
		world.explode(b);
	}

	public void setMyPos(int x, int y) {
		world.getEntityManager().addPlayer(x, y, 0);
	}

	public void addPlayer(int x, int y) {
		world.addPlayer(x, y);
	}

	public void gameOver(boolean win) {
		getGame().gameOver(win);
	}

	public synchronized void setPlayerPos(float x, float y, int who) {
		// System.out.println("WHO: " + who + " / X: " + x + " / Y: " + y);

		Player play = getWorld().getEntityManager().getPlayer(who);

		play.setxMove(0);
		play.setyMove(0);

		float xprev = play.getX();
		float yprev = play.getY();

		if ((xprev - x) < 0) { // going right
			play.setxMove(play.getSpeed());
		}
		if ((xprev - x) > 0) { // going left
			play.setxMove(-play.getSpeed());
		}
		if ((yprev - y) < 0) { // going down
			play.setyMove(play.getSpeed());
		}
		if ((yprev - y) > 0) { // going up
			play.setyMove(-play.getSpeed());
		}

		play.setX(x);
		play.setY(y);
	}

	public float getPlayerPosX(int who) {
		float x = world.getEntityManager().getPlayer(who).getX();
		return x;
	}

	public float getPlayerPosY(int who) {
		float y = world.getEntityManager().getPlayer(who).getY();
		return y;
	}
}
