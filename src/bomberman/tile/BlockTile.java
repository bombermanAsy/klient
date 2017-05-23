package bomberman.tile;

import bomberman.gfx.Assets;

public class BlockTile extends Tile {

	public BlockTile(int id) {
		super(Assets.block, id);
	}

	public boolean isSolid() {
		return true;
	}
}
