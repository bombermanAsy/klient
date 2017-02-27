package bomberman.entity.items;

import java.awt.Graphics;

import bomberman.Handler;
import bomberman.gfx.Animation;
import bomberman.gfx.Assets;

public class Bomb extends Item {

	//ANIMATIONS
	private Animation bombAnim;
	
	public Bomb(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		collisionBox.x = 1;
		collisionBox.y = 1;
		collisionBox.width = 49;
		collisionBox.height = 49;
		
		//ANIMATIONS
		bombAnim = new Animation(250, Assets.bomb);
	}

	@Override
	public void tick() {
		bombAnim.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(bombAnim.getCurrentFrame(), (int) x, (int) y, width, height, null);
	}

}
