package bomberman.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys;
	
	public boolean up, down, left, right, 
					WUp, SDown, ALeft, DRight, 
					num8, num5, num4, num6,
					space;
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void tick() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		
		WUp = keys[KeyEvent.VK_W];
		SDown = keys[KeyEvent.VK_S];
		ALeft = keys[KeyEvent.VK_A];
		DRight = keys[KeyEvent.VK_D];
		
		num8 = keys[KeyEvent.VK_NUMPAD8];
		num5 = keys[KeyEvent.VK_NUMPAD5];
		num4 = keys[KeyEvent.VK_NUMPAD4];
		num6 = keys[KeyEvent.VK_NUMPAD6];
		
		space = keys[KeyEvent.VK_SPACE];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}

}
