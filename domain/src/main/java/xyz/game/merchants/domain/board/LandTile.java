package xyz.game.merchants.domain.board;

public abstract class LandTile extends Tile {
    LandTile(TileCoordinate c){
        super(c);
    }
    LandTile(TileCoordinate c, TileNumber t){
        super(c, t);
    }
}