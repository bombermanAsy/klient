package bomberman.entity.items;

import java.awt.Graphics;

import bomberman.Handler;
import bomberman.gfx.Animation;
import bomberman.gfx.Assets;
import bomberman.entity.creatures.*;

public class Bomb extends Item {

	//ANIMATIONS
	private Animation bombAnim;
	private int lifeTime = 100;
	private Player whoPlantMe;
	
	public Bomb(Handler handler, float x, float y, int width, int height, Player a) {
		super(handler, x, y, width, height);
		
		whoPlantMe = a;
		
		collisionBox.x = 1;
		collisionBox.y = 1;
		collisionBox.width = 49;
		collisionBox.height = 49;
		
		//ANIMATIONS
		bombAnim = new Animation(250, Assets.bomb);
	}
	
	public Player getWhoPlantMe() {
		return whoPlantMe;
	}

	@Override
	public void tick() {
		bombAnim.tick();
		lifeTime--;
		if (lifeTime == 0) handler.explode(this);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(bombAnim.getCurrentFrame(), (int) x, (int) y, width, height, null);
	}

}
