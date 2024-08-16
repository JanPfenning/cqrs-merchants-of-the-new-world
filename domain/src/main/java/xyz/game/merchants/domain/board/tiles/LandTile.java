package xyz.game.merchants.domain.board.tiles;

public abstract class LandTile extends Tile {
    protected LandTile(TileCoordinate c){
        super(c);
    }
    protected LandTile(TileCoordinate c, TileNumber t){
        super(c, t);
    }
}