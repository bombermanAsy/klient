package bomberman;

import bomberman.input.KeyManager;
import bomberman.input.MouseManager;
import bomberman.worlds.World;
import bomberman.entity.items.Bomb;
import bomberman.entity.creatures.*;

public class Handler {

	
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
	
	public void plantBomb(Creature a, float x, float y) {
		this.world.plantBomb(a, x, y);
	}
	
	public void explode(Bomb b) {
		world.explode(b);
	}
}
