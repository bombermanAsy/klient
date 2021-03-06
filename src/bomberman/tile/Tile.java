package bomberman.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	//STATIC STUFF
	
	public static Tile[] tiles = new Tile [50];
	
	public static Tile grassTile = new GrassTile(0);
	public static Tile blockTile = new BlockTile(1);
	public static Tile brickTile = new BrickTile(2);
	
	//CLASS
	
	public static final int TILE_WIDTH = 50, TILE_HEIGHT = 50;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile (BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getId() {
		return id;
	}
}
