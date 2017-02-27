package bomberman.entity.statics;

import java.awt.Graphics;

import bomberman.Handler;
import bomberman.gfx.Assets;
import bomberman.tile.Tile;

public class Brick extends StaticEntity {

	public Brick(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.brick, (int) x, (int) y, width, height, null);
	}

}
