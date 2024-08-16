package xyz.game.merchants.domain.board.tiles;

import lombok.Getter;
import xyz.game.merchants.domain.board.Resource;

public class ResourceTile extends LandTile {
    @Getter
    private final Resource resource;
    protected ResourceTile(TileCoordinate c, TileNumber t, Resource r){
        super(c, t);
        this.resource = r;
    }
}