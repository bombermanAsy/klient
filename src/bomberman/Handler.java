package bomberman;

import java.util.ArrayList;
import java.util.Iterator;

import bomberman.entity.EntityManager;
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
		World w = getWorld();
		EntityManager e = w.getEntityManager();
		int n = e.getMyNum();
		
		e.getPlayer(who).setX(x);
		e.getPlayer(who).setY(y);
		
		/*for (Player xx : world.getEntityManager().getPlayers()) {
			if (xx == null) System.out.println("null");
			else System.out.println(xx.getNumber());
		}
		if (who != getWorld().getEntityManager().getMyNum()) {
			Player play = world.getEntityManager().getPlayer(who);		
			
			float xprev = play.getX();
			float yprev = play.getY();
			
			if (Math.abs(xprev-x) > Math.abs(yprev-y)) {
				if (xprev - x < 0) {
					play.setxMove(1);
				} else {
					play.setxMove(-1);
				}
			} else {
				if (yprev - y < 0) {
					play.setyMove(1);
				} else {
					play.setyMove(-1);
				}
			} else {
				play.setxMove(0);
				play.setyMove(0);
			}
			
			play.setX(x);
			play.setY(y);
		}*/
		
/*		if (ConnectionHandler.canISend == true) {
			if (EntityManager.canIAlready == true) {
				ArrayList<Player> pl = world.getEntityManager().getPlayers();
				for (Iterator<Player> it = pl.iterator(); it.hasNext();) {
					Player type = (Player) it.next();
					if (type != null) {
						if (type.player_num == who) {
							type.setX(x);
							type.setY(y);
						}
					}
				}
				
			}
		}*/
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
