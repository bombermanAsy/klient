package bomberman.state;

import java.awt.Graphics;

import bomberman.Handler;
import bomberman.entity.items.Bomb;
import bomberman.worlds.World;

public class GameState extends State {

//	private Player1 player1;
//	private Player2 player2;
//	private Player3 player3;
	
	private Bomb bombTest;
	
	private World standardWorld;
	//private Brick brick;
	
	public GameState(Handler handler) {
		super(handler);
		standardWorld = new World(handler, "res/worlds/standard.txt");
		handler.setWorld(standardWorld);
//		player1 = new Player1(handler, 50, 50);
//		player2 = new Player2(handler, 650, 550);
//		player3 = new Player3(handler, 50, 550);
		
		//brick = new Brick(handler, 50, 100);
		
		bombTest = new Bomb(handler, 100, 50, 50, 50);
	}
	
	@Override
	public void tick() {
		standardWorld.tick();
//		player1.tick();
//		player2.tick();
//		player3.tick();
		
		//brick.tick();
		
		bombTest.tick();
	}

	@Override
	public void render(Graphics g) {
		//g.drawImage(Assets.block, 0, 0, null);
		//Tile.tiles[2].render(g, 100, 100);
		standardWorld.render(g);
//		player1.render(g);
//		player2.render(g);
//		player3.render(g);
		
		//brick.render(g);
		
		bombTest.render(g);
	}
}
