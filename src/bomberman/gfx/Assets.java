package bomberman.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 50, height = 50;
	
	public static BufferedImage grass, block, brick;
	public static BufferedImage[] player1_down, player1_up, player1_left, player1_right;
	public static BufferedImage[] player2_down, player2_up, player2_left, player2_right;
	public static BufferedImage[] player3_down, player3_up, player3_left, player3_right;
	public static BufferedImage[] menuButtons;
	public static BufferedImage[] bomb, bombExplosion;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/BackgroundTiles.png"));
		
		grass = sheet.crop(0, 0, width, height);
		block = sheet.crop(width, 0, width, height);
		brick = sheet.crop(2 * width, 0, width, height);
	
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Player1Anim.png"));
		
		player1_down = new BufferedImage[3];
		player1_up = new BufferedImage[3];
		player1_left = new BufferedImage[3];
		player1_right = new BufferedImage[3];
		
		player1_down[0] = sheet.crop(0, 0, width, height);
		player1_down[1] = sheet.crop(50, 0, width, height);
		player1_down[2] = sheet.crop(100, 0, width, height);
		player1_up[0] = sheet.crop(0, 50, width, height);
		player1_up[1] = sheet.crop(50, 50, width, height);
		player1_up[2] = sheet.crop(100, 50, width, height);
		player1_left[0] = sheet.crop(0, 100, width, height);
		player1_left[1] = sheet.crop(50, 100, width, height);
		player1_left[2] = sheet.crop(100, 100, width, height);
		player1_right[0] = sheet.crop(0, 150, width, height);
		player1_right[1] = sheet.crop(50, 150, width, height);
		player1_right[2] = sheet.crop(100, 150, width, height);
		
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Player2Anim.png"));
		
		player2_down = new BufferedImage[3];
		player2_up = new BufferedImage[3];
		player2_left = new BufferedImage[3];
		player2_right = new BufferedImage[3];
		
		player2_down[0] = sheet.crop(0, 0, width, height);
		player2_down[1] = sheet.crop(50, 0, width, height);
		player2_down[2] = sheet.crop(100, 0, width, height);
		player2_up[0] = sheet.crop(0, 50, width, height);
		player2_up[1] = sheet.crop(50, 50, width, height);
		player2_up[2] = sheet.crop(100, 50, width, height);
		player2_left[0] = sheet.crop(0, 100, width, height);
		player2_left[1] = sheet.crop(50, 100, width, height);
		player2_left[2] = sheet.crop(100, 100, width, height);
		player2_right[0] = sheet.crop(0, 150, width, height);
		player2_right[1] = sheet.crop(50, 150, width, height);
		player2_right[2] = sheet.crop(100, 150, width, height);
		
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Player3Anim.png"));
		
		player3_down = new BufferedImage[3];
		player3_up = new BufferedImage[3];
		player3_left = new BufferedImage[3];
		player3_right = new BufferedImage[3];
		
		player3_down[0] = sheet.crop(0, 0, width, height);
		player3_down[1] = sheet.crop(50, 0, width, height);
		player3_down[2] = sheet.crop(100, 0, width, height);
		player3_up[0] = sheet.crop(0, 50, width, height);
		player3_up[1] = sheet.crop(50, 50, width, height);
		player3_up[2] = sheet.crop(100, 50, width, height);
		player3_left[0] = sheet.crop(0, 100, width, height);
		player3_left[1] = sheet.crop(50, 100, width, height);
		player3_left[2] = sheet.crop(100, 100, width, height);
		player3_right[0] = sheet.crop(0, 150, width, height);
		player3_right[1] = sheet.crop(50, 150, width, height);
		player3_right[2] = sheet.crop(100, 150, width, height);
		
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/MenuButtons.png"));
		
		menuButtons = new BufferedImage[2];
		
		menuButtons[0] = sheet.crop(0, 0, 2*width, height);
		menuButtons[1] = sheet.crop(100, 0, 2*width, height);
		
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/BombAnim.png"));
		
		bomb = new BufferedImage[7];
		
		bomb[0] = sheet.crop(0, 0, width, height);
		bomb[1] = sheet.crop(50, 0, width, height);
		bomb[2] = sheet.crop(100, 0, width, height);
		bomb[3] = sheet.crop(150, 0, width, height);
		bomb[4] = sheet.crop(0, 50, width, height);
		bomb[5] = sheet.crop(50, 50, width, height);
		bomb[6] = sheet.crop(100, 50, width, height);
		
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/explosion.png"));
		
		bombExplosion = new BufferedImage[16];
		
		bombExplosion[0] = sheet.crop(0, 0, width, height);
		bombExplosion[1] = sheet.crop(50, 0, width, height);
		bombExplosion[2] = sheet.crop(100, 0, width, height);
		bombExplosion[3] = sheet.crop(150, 0, width, height);
		bombExplosion[4] = sheet.crop(0, 50, width, height);
		bombExplosion[5] = sheet.crop(50, 50, width, height);
		bombExplosion[6] = sheet.crop(100, 50, width, height);
		bombExplosion[7] = sheet.crop(150, 50, width, height);
		bombExplosion[8] = sheet.crop(0, 100, width, height);
		bombExplosion[9] = sheet.crop(50, 100, width, height);
		bombExplosion[10] = sheet.crop(100, 100, width, height);
		bombExplosion[11] = sheet.crop(150, 100, width, height);
		bombExplosion[12] = sheet.crop(0, 150, width, height);
		bombExplosion[13] = sheet.crop(50, 150, width, height);
		bombExplosion[14] = sheet.crop(100, 150, width, height);
		bombExplosion[15] = sheet.crop(150, 150, width, height);
		
//		player1 = sheet.crop(0, 0, width, height);
//		player2 = sheet.crop(50, 0, width, height);
//		player3 = sheet.crop(0, 50, width, height);
	}
}
