package bomberman;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import bomberman.display.Display;
import bomberman.gfx.Assets;
import bomberman.input.KeyManager;
import bomberman.input.MouseManager;
import bomberman.state.GameState;
import bomberman.state.MenuState;
import bomberman.state.State;

public class Game implements Runnable{

	private Display display;
	
	//public static int width, height;
	public int width, height;
	private String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//private BufferedImage backgroundTiles;
	//private SpriteSheet sheet;
	
	//STATES
	public State gameState;
	public State menuState;
	
	//INPUT
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//HANDLER
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}

	private void init() {
		
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		//backgroundTiles = ImageLoader.loadImage("/textures/BackgroundTiles.png");
		//sheet = new SpriteSheet(backgroundTiles);
		Assets.init();
		
		handler = new Handler(this);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
//		State.setState(gameState);
		State.setState(menuState);
	}
	
	
	private void tick() {
		keyManager.tick();
		
		if(State.getState() != null) {
			State.getState().tick();
		}
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//================================
		g.clearRect(0, 0, width, height);
		//================================
		
		//g.drawImage(sheet.crop(0, 0, 50, 50), 0, 0, null);
		//g.drawImage(sheet.crop(50, 0, 50, 50), 100, 0, null);
		//g.drawImage(sheet.crop(100, 0, 50, 50), 200, 0, null);
		
		/*g.drawImage(Assets.grass, x, 0, null);
		g.drawImage(Assets.block, 100, 0, null);
		g.drawImage(Assets.bricks, 200, 0, null);
		g.drawImage(Assets.player1, 300, 0, null);
		g.drawImage(Assets.bricks, 400, 0, null);*/
		
		if(State.getState() != null) {
			State.getState().render(g);
		}
		
		//================================
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;  // 1000000000 nsec = 1 sec
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000) {
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public synchronized void start() {
		
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		if(!running) return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
