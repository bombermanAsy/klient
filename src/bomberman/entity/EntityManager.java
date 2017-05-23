package bomberman.entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import bomberman.Handler;
import bomberman.entity.creatures.Player;
import bomberman.entity.items.Bomb;

public class EntityManager {

	private Handler handler;
	private ArrayList<Player> players;
	private Player me = null;
	private ArrayList<Entity> entities;
	
	public static boolean canIAlready = false;
	
	public EntityManager(Handler handler) {
		this.handler = handler;

		players = new ArrayList<Player>();
		for (int i=0; i<4; i++) {
			players.add(null);
		}
		
		//int[] myPos = handler.getGame().getConnectionHandler().getPositions();

		//players.set(0, new Player(handler, myPos[0], myPos[1]));
		
		
		int[] positions = handler.getGame().getConnectionHandler().getPositions();
		int[] pl_num = handler.getGame().getConnectionHandler().getPl_num();
		int gracze = handler.getGame().getConnectionHandler().getGracze();
		
		
		for (int i=0; i<gracze; i++) {
			addPlayer(positions[2*pl_num[i]], positions[2*pl_num[i]+1], i);
		}

		entities = new ArrayList<Entity>(gracze);
		canIAlready = true;
		
	}

	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}

		me.tick();
	}

	public void render(Graphics g) {
		for (Entity e : entities) {
			e.render(g);
		}

		for (Player x : players)
			if (x != null) {
				if (x.isAlive() == true) {
					x.render(g);
				} 
			}
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void removeBomb(Bomb b) {
		entities.remove(b);
		for (Player x : players) {
			if (x != null) {
				if (Math.abs(x.getX() - b.getX()) < 50 && Math.abs(x.getY() - b.getY()) < 50) {
					if (x == me) {
						handler.getGame().getConnectionHandler().iDied();
						handler.gameOver(false);
					}
					x.setAlive(false);
					//x = null;
				} 
			}
		}
		
		if (b.isThisMine()) me.addOne();
		
		int zywi = 0;
		Player left = null;
		for (Player x : players) {
			if (x != null && x.isAlive()) {
				zywi++;
				left = x;
			}
		}
		
		if (zywi==1 && left == me) {
			handler.gameOver(true);
		}
	}

	// GETTERS AND SETTERS
	
	public int getMyNum() {
		return me.player_num;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer(int i) {
		return players.get(i);
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	public void addPlayer(int x, int y, int nr) {
		int[] pl_num = handler.getGame().getConnectionHandler().getPl_num();
		if (nr == 0) {
			me = new Player(handler, x, y, true);
			players.set(pl_num[nr], me);
		}
		else  {
			Player other = new Player(handler, x, y, false);
			players.set(pl_num[nr], other);
		}
		
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
}
