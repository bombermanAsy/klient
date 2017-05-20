package bomberman;

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
		world.getEntityManager().addPlayer(x, y);
	}

	public void addPlayer(int x, int y) {
		world.addPlayer(x, y);
	}

	public void gameOver(boolean win) {
		getGame().gameOver(win);
	}

	public void setPlayerPos(float x, float y, int who) {
		world.getEntityManager().getPlayer(who).setX(x);
		world.getEntityManager().getPlayer(who).setY(y);
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
