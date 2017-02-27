package bomberman.entity.creatures;

import bomberman.Handler;
import bomberman.entity.Entity;
import bomberman.tile.Tile;

public abstract class Creature extends Entity {
	
	public static final int DEFAULT_HEALTH = 100;
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 50;
	public static final int DEFAULT_CREATURE_HEIGHT = 50;

	protected int health;
	protected float speed;
	protected float xMove, yMove;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	public void moveX() {
		//x += xMove;
		
		if(xMove > 0) { // moving right
			int tx = (int) (x + xMove + collisionBox.x + collisionBox.width) / Tile.TILE_WIDTH;
			
			if (!collisionWithTiles(tx, (int) (y + collisionBox.y) / Tile.TILE_HEIGHT) && 
					!collisionWithTiles(tx, (int) (y + collisionBox.y + collisionBox.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILE_WIDTH - collisionBox.x - collisionBox.width - 1;
			}
		} else if (xMove < 0) { //moving left
			int tx = (int) (x + xMove + collisionBox.x) / Tile.TILE_WIDTH;
			
			if (!collisionWithTiles(tx, (int) (y + collisionBox.y) / Tile.TILE_HEIGHT) && 
					!collisionWithTiles(tx, (int) (y + collisionBox.y + collisionBox.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - collisionBox.x;
			}
		}
	}
	public void moveY() {
		//y += yMove;
		
		if(yMove < 0 ) { // moving Up
			
			int ty = (int) (y + yMove + collisionBox.y) / Tile.TILE_HEIGHT;
			
			if(!collisionWithTiles((int) (x + collisionBox.x) / Tile.TILE_WIDTH, ty) && 
					!collisionWithTiles((int) (x + collisionBox.x + collisionBox.width) / Tile.TILE_WIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - collisionBox.y;
			}
		} else if(yMove > 0) { // moving Down
			int ty = (int) (y + yMove + collisionBox.y + collisionBox.height) / Tile.TILE_HEIGHT;
			
			if(!collisionWithTiles((int) (x + collisionBox.x) / Tile.TILE_WIDTH, ty) && 
					!collisionWithTiles((int) (x + collisionBox.x + collisionBox.width) / Tile.TILE_WIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILE_HEIGHT - collisionBox.y - collisionBox.height - 1;
			}
		}
	}
	public void move() {
		moveX();
		moveY();
	}
	
	protected boolean collisionWithTiles(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	//==== GETTERS AND SETTERS
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	
}
