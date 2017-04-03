/*package bomberman.entity.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import bomberman.Handler;
import bomberman.gfx.Animation;
import bomberman.gfx.Assets;


public class Player1 extends Creature {

	//ANIMATIONS
	private Animation animDown;
	private Animation animUp;
	private Animation animLeft;
	private Animation animRight;
	
	public Player1(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		collisionBox.x = 16;
		collisionBox.y = 24;
		collisionBox.width = 17;
		collisionBox.height = 19;
		collisionBox.x = 1;
		collisionBox.y = 1;
		collisionBox.width = 48;
		collisionBox.height = 48;
		
		//ANIMATIONS
		animDown = new Animation(500, Assets.player1_down);
		animUp = new Animation(500, Assets.player1_up);
		animLeft = new Animation(500, Assets.player1_left);
		animRight = new Animation(500, Assets.player1_right);
	}

	@Override
	public void tick() {
		//ANIMATIONS
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
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
		if (numOfBombs > 0) {
			handler.plantBomb(this, this.x, this.y);
			numOfBombs--;
		}
	}
	
	

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
		g.setColor(Color.red);
//		g.fillRect((int) (x + collisionBox.x), (int) (y + collisionBox.y), 
//				collisionBox.width, collisionBox.height);
		g.fillOval((int) (x + collisionBox.x), (int) (y + collisionBox.y), 
				collisionBox.width, collisionBox.height);
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
			return Assets.player1_down[0];
		}
	}

}
*/