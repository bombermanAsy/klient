package bomberman.entity.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import bomberman.Handler;
import bomberman.entity.Entity;
import bomberman.gfx.Animation;
import bomberman.gfx.Assets;
import bomberman.tile.Tile;

public class Player extends Entity {
	
	public static final int DEFAULT_HEALTH = 100;
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 50;
	public static final int DEFAULT_CREATURE_HEIGHT = 50;
	
	public static int numOfPlayers = 0;
	public int playersNum;

	protected int health;
	protected float speed;
	protected float xMove, yMove;	
	protected int numOfBombs;
	
	private final static int PLANT_DELAY_TIME = 25;
	
	protected static int plantDelay = PLANT_DELAY_TIME;
	protected static boolean canIPlant = true;
	
	//ANIMATIONS
	private Animation animDown;
	private Animation animUp;
	private Animation animLeft;
	private Animation animRight;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
		numOfBombs = 5;
		
		collisionBox.x = 16;
		collisionBox.y = 24;
		collisionBox.width = 17;
		collisionBox.height = 19;
		
		playersNum = numOfPlayers;
		numOfPlayers++;
		
		//ANIMATIONS
		if(playersNum == 0) {
			animDown = new Animation(500, Assets.player1_down);
			animUp = new Animation(500, Assets.player1_up);
			animLeft = new Animation(500, Assets.player1_left);
			animRight = new Animation(500, Assets.player1_right);
		} else {
			animDown = new Animation(500, Assets.player2_down);
			animUp = new Animation(500, Assets.player2_up);
			animLeft = new Animation(500, Assets.player2_left);
			animRight = new Animation(500, Assets.player2_right);			
		}
	}
	
	public int getNumber() {
		return playersNum;
	}
	
	@Override
	public void tick() {
		//ANIMATIONS
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		
		if (canIPlant == false) {
			plantDelay--;
			if (plantDelay == 0) {
				canIPlant = true;
				plantDelay = PLANT_DELAY_TIME;
			}
		}
		//MOVEMENT	
		getInput();
		move();
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up) {
			yMove = -speed;
		}
		if(handler.getKeyManager().down) {
			yMove = speed;
		}
		if(handler.getKeyManager().left) {
			xMove = -speed;
		}
		if(handler.getKeyManager().right) {
			xMove = speed;
		}
		if(handler.getKeyManager().space) {
			plantBomb();
		}
		
	}
	
	private void plantBomb() {
		if (numOfBombs > 0 && canIPlant == true) {
			handler.plantBomb(this, this.x, this.y);
			numOfBombs--;
			canIPlant = false;
		}
	}
	
	
	public void addOne() {
		numOfBombs++;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if(xMove > 0) {
			return animRight.getCurrentFrame();
		} else if(yMove < 0) {
			return animUp.getCurrentFrame();
		} else if(yMove > 0) {
			return animDown.getCurrentFrame();
		} else {
			if (playersNum == 0) return Assets.player1_down[0];
			else return Assets.player2_down[0];
		}
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
