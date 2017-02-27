package bomberman.entity;

import java.awt.Graphics;
import java.util.ArrayList;

import bomberman.Handler;
import bomberman.entity.creatures.Player1;
import bomberman.entity.creatures.Player2;
import bomberman.entity.creatures.Player3;

public class EntityManager {

	private Handler handler;
	private Player1 player1;
	private Player2 player2;
	private Player3 player3;
	private ArrayList<Entity> entities;
	
	public EntityManager(Handler handler, Player1 player1, Player2 player2, Player3 player3) {
		this.handler = handler;
		this.player1 = player1;
		this.player2 = player2;
		this.player3 = player3;
		
		entities = new ArrayList<Entity>();
	}
	
	public void tick() {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		player1.tick();
		player2.tick();
		player3.tick();
	}
	
	public void render(Graphics g) {
		for(Entity e : entities) {
			e.render(g);
		}
		player1.render(g);
		player2.render(g);
		player3.render(g);
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	//GETTERS AND SETTERS
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player1 getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player1 player1) {
		this.player1 = player1;
	}

	public Player2 getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player2 player2) {
		this.player2 = player2;
	}

	public Player3 getPlayer3() {
		return player3;
	}

	public void setPlayer3(Player3 player3) {
		this.player3 = player3;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
