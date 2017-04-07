package bomberman.worlds;

import java.awt.Graphics;


import bomberman.Handler;
import bomberman.entity.EntityManager;
import bomberman.entity.creatures.*;
import bomberman.entity.items.Bomb;
import bomberman.tile.Tile;
import bomberman.utils.Utils;

public class World {

	protected Handler handler;
	private int width, height;
	private int[] spawnX = new int[3],
					spawnY = new int[3];
	private int[][] tiles;
	
	//ENTITIES
	private EntityManager entityManager;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		
		entityManager = new EntityManager(handler);
		
		loadWorld(path);
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
	
	public void plantBomb(Player a, float x, float y) {
		entityManager.addEntity(new Bomb(handler, x, y, 50, 50, a));
	}
	
	public void explode(Bomb b) {
		entityManager.removeBomb(b);
	}
	
	
	public void loadWorld(String path) {
		
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		
		spawnX[0] = Utils.parseInt(tokens[2]);
		spawnY[0] = Utils.parseInt(tokens[3]);
		spawnX[1] = Utils.parseInt(tokens[4]);
		spawnY[1] = Utils.parseInt(tokens[5]);
		spawnX[2] = Utils.parseInt(tokens[6]);
		spawnY[2] = Utils.parseInt(tokens[7]);
		
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

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void addPlayer(int x, int y) {
		entityManager.addPlayer(x,  y);
	}


}


