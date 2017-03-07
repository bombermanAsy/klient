package bomberman.entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.lang.Math.*;

import bomberman.Handler;
import bomberman.entity.creatures.*;
import bomberman.entity.items.Bomb;

public class EntityManager {

	private Handler handler;
	//private Player1 player1;
	//private Player2 player2;
	//private Player3 player3;
	private ArrayList<Creature> players;
	private ArrayList<Entity> entities;
	
	public EntityManager(Handler handler, Player1 player1, Player2 player2, Player3 player3) {
		this.handler = handler;
		
		players = new ArrayList<Creature>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		
		entities = new ArrayList<Entity>();
	}
	
	public void tick() {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		
		for (Creature x : players) x.tick();
	}
	
	public void render(Graphics g) {
		for(Entity e : entities) {
			e.render(g);
		}
		
		for (Creature x : players) x.render(g);
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removeBomb(Bomb b) {
		entities.remove(b);
		ArrayList<Creature> deleteMe = new ArrayList<Creature>();
		for (Creature x : players) {
			if (Math.abs(x.getX() - b.getX()) < 50 &&
					Math.abs(x.getY() - b.getY()) < 50) {
				deleteMe.add(x);
			}
		}
		for (Creature x : deleteMe) {
			players.remove(x);
		}
		for (Creature x :players) {
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

	public Creature getPlayer1() {
		return players.get(0);
	}

	public void setPlayer1(Player1 player1) {
		this.players.set(0, player1);
	}

	public Creature getPlayer2() {
		return players.get(1);
	}

	public void setPlayer2(Player2 player2) {
		this.players.set(1, player2);
	}

	public Creature getPlayer3() {
		return players.get(2);
	}

	public void setPlayer3(Player3 player3) {
		players.set(2, player3);
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
