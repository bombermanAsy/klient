package bomberman.state;

import java.awt.Graphics;

import bomberman.Handler;
import bomberman.gfx.Assets;
import bomberman.ui.ClickListener;
import bomberman.ui.UIImageButton;
import bomberman.ui.UIManager;
import bomberman.ui.UILabel;

public class MenuState extends State {

	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUiManager(uiManager);
		
		uiManager.addObject(new UIImageButton(handler.getGame().width / 2 - 50,
				handler.getGame().height / 2 - 50, 100, 50,
				Assets.menuButtons, new ClickListener() {
					
					@Override
					public void onclick() {
						handler.getMouseManager().setUiManager(null);
						State.setState(handler.getGame().gameState);
					}
				}));
	}
	
	public void waiting() {
		uiManager.addObject(new UILabel(100,100,100,100));
	}
	
	
	@Override
	public void tick() {
		//System.out.println(handler.getMouseManager().getMouseX() + "        " + handler.getMouseManager().getMouseY());
		
//		handler.getMouseManager();
//		if(MouseManager.isLeftPressed() && handler.getMouseManager().isRightPressed()) {
//			State.setState(handler.getGame().gameState);
//		}
		
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(Color.BLACK);
		//g.drawString("MENU", Game.width/2, Game.height/2);
		
		uiManager.render(g);
	}

}
