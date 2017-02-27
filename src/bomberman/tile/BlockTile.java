package bomberman.tile;

import java.awt.image.BufferedImage;

import bomberman.gfx.Assets;

public class BlockTile extends Tile {

	public BlockTile(int id) {
		super(Assets.block, id);
	}

	public boolean isSolid() {
		return true;
	}
}
