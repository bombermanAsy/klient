package bomberman.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	
	private int speed, index;
	private BufferedImage[] frames;
	private long timer, lastTime;
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed= speed;
		this.frames = frames;
		index = 1;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed) {
			index++;
			timer = 0;
			
			if(index >= frames.length)
				index = 1;
		}
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
}