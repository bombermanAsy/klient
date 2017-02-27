package bomberman.worlds;

import java.awt.Graphics;

import bomberman.Handler;
import bomberman.entity.EntityManager;
import bomberman.entity.creatures.Player1;
import bomberman.entity.creatures.Player2;
import bomberman.entity.creatures.Player3;
import bomberman.tile.Tile;
import bomberman.utils.Utils;

public class World {

	protected Handler handler;
	private int width, height;
	private int p1_spawnX, p1_spawnY,
				p2_spawnX, p2_spawnY,
				p3_spawnX, p3_spawnY;
	private int[][] tiles;
	
	//ENTITIES
	private EntityManager entityManager;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		
		entityManager = new EntityManager(handler,
				new Player1(handler, 50, 50),
				new Player2(handler, 650, 550),
				new Player3(handler, 50, 550));
		
		loadWorld(path);
		
		entityManager.getPlayer1().setX(p1_spawnX);
		entityManager.getPlayer1().setY(p1_spawnY);
		
		entityManager.getPlayer2().setX(p2_spawnX);
		entityManager.getPlayer2().setY(p2_spawnY);
		
		entityManager.getPlayer3().setX(p3_spawnX);
		entityManager.getPlayer3().setY(p3_spawnY);
	}
	
	public void tick() {
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				getTile(x, y).render(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
			}
		}
		
		//ENTITIES
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		
		if(t == null)
			return Tile.grassTile;
		return t;
	}
	
	public void loadWorld(String path) {
		
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		p1_spawnX = Utils.parseInt(tokens[2]);
		p1_spawnY = Utils.parseInt(tokens[3]);
		p2_spawnX = Utils.parseInt(tokens[4]);
		p2_spawnY = Utils.parseInt(tokens[5]);
		p3_spawnX = Utils.parseInt(tokens[6]);
		p3_spawnY = Utils.parseInt(tokens[7]);
		
		tiles = new int[width][height];
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 8]);
			}
		}
		
		/*//=======================================
		width = 5;
		height = 5;
		tiles = new int[width][height];
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				tiles[x][y] = 2;
			}
		}
		*///=======================================
	}
}
