package bomberman.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class UILabel extends UIObject {
	
	private String text;
	
	public UILabel(float x, float y, int width, int height) {  
		super(x,y,width,height);
		text = "Oczekiwanie na pozosta³ych graczy...";
	}
	
	public void tick() {};
	
	public void render(Graphics g) {
		g.drawString(this.text, 50, 50);
	};
	
	public void onClick() {};
	
	public void onMouseMove(MouseEvent e) {};
	
	public void onMouseRelease(MouseEvent e) {};
	
}
