package bomberman.tile;

import java.awt.image.BufferedImage;

import bomberman.gfx.Assets;

public class BrickTile extends Tile {

	public BrickTile(int id) {
		super(Assets.brick, id);
	}
	
	public boolean isSolid() {
		return true;
	}
}
