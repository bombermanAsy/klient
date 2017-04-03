package bomberman.entity.items;

import java.awt.Graphics;

import bomberman.Handler;
import bomberman.gfx.Animation;
import bomberman.gfx.Assets;
import bomberman.entity.creatures.*;

public class Bomb extends Item {

	// ANIMATIONS
	private Animation bombAnim;
	private Animation bombExplosion;

	// VARIABLES
	private int lifeTime = 100;
	private int explosionTime = 30;
	private Player whoPlantMe;
	private static boolean shouldIExplode = false;

	public Bomb(Handler handler, float x, float y, int width, int height, Player a) {
		super(handler, x, y, width, height);

		whoPlantMe = a;

		collisionBox.x = 1;
		collisionBox.y = 1;
		collisionBox.width = 49;
		collisionBox.height = 49;

		// ANIMATIONS
		bombAnim = new Animation(250, Assets.bomb);
		bombExplosion = new Animation(50, Assets.bombExplosion);
	}

	public Player getWhoPlantMe() {
		return whoPlantMe;
	}

	@Override
	public void tick() {
		bombAnim.tick();
		lifeTime--;
		if (lifeTime <= 0) {
			shouldIExplode = true;
			explosionTime--;
			if (explosionTime == 0) {
				shouldIExplode = false;
				handler.explode(this);
			}
		}
		if (shouldIExplode == true) {
			bombExplosion.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		
		if (shouldIExplode == true) {
			g.drawImage(bombExplosion.getCurrentFrame(), (int) x, (int) y, width, height, null);
			g.drawImage(bombExplosion.getCurrentFrame(), (int) x + 50, (int) y, width, height, null);
			g.drawImage(bombExplosion.getCurrentFrame(), (int) x + 50, (int) y + 50, width, height, null);
			g.drawImage(bombExplosion.getCurrentFrame(), (int) x, (int) y + 50, width, height, null);
			g.drawImage(bombExplosion.getCurrentFrame(), (int) x - 50, (int) y + 50, width, height, null);
			g.drawImage(bombExplosion.getCurrentFrame(), (int) x - 50, (int) y, width, height, null);
			g.drawImage(bombExplosion.getCurrentFrame(), (int) x - 50, (int) y - 50, width, height, null);
			g.drawImage(bombExplosion.getCurrentFrame(), (int) x, (int) y - 50, width, height, null);
			g.drawImage(bombExplosion.getCurrentFrame(), (int) x + 50, (int) y - 50, width, height, null);
		} else {
			g.drawImage(bombAnim.getCurrentFrame(), (int) x, (int) y, width, height, null);
		}
	}

}
