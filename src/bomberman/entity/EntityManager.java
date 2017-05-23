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
		for (Player pl : players) {
			if (pl != null) {
				System.out.println("Pl number: " + pl.player_num);
				System.out.println("Pl pos: " + pl.getX() + " " + pl.getY());
				if (me == pl) System.out.println("JA");
			}
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
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			Player x = it.next();
			if (x != null) {
				if (Math.abs(x.getX() - b.getX()) < 50 && Math.abs(x.getY() - b.getY()) < 50) {
					it.remove();
					if (x == me) {
						handler.getGame().getConnectionHandler().iDied();
						handler.gameOver(false);
					}
				} 
			}
		}
		
		if (b.isThisMine()) me.addOne();
		
		if (players.size() == 1 && players.get(0) == me) {
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
			System.out.println("ME :: " + me.player_num);
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
