package bomberman.entity;

import java.awt.Graphics;
import java.util.ArrayList;
import bomberman.Handler;
import bomberman.entity.creatures.*;
import bomberman.entity.items.Bomb;

public class EntityManager {

	private Handler handler;
	private ArrayList<Player> players;
	private ArrayList<Entity> entities;
	
	public EntityManager(Handler handler) {
		this.handler = handler;
		
		players = new ArrayList<Player>();
		
		players.add(new Player(handler, 50, 50));
		//players.add(new Player(handler, 650, 550));
		//players.add(new Player(handler, 50, 550));
		
		entities = new ArrayList<Entity>();
	}
	
	public void tick() {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		
		for (Player x : players) x.tick();
	}
	
	public void render(Graphics g) {
		for(Entity e : entities) {
			e.render(g);
		}
		
		for (Player x : players) x.render(g);
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removeBomb(Bomb b) {
		entities.remove(b);
		ArrayList<Player> deleteMe = new ArrayList<Player>();
		for (Player x : players) {
			if (Math.abs(x.getX() - b.getX()) < 50 &&
					Math.abs(x.getY() - b.getY()) < 50) {
				deleteMe.add(x);
			}
		}
		for (Player x : deleteMe) {
			players.remove(x);
		}
		for (Player x :players) {
			if (x == b.getWhoPlantMe()) {
				x.addOne();
				break;
			}
		}
		
	}
	
	//GETTERS AND SETTERS
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer(int i) {
		for (Player x : players) {
			if (x.getNumber() == i) return x;
		}
		return null;
	}


	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
