package xyz.game.board;

import lombok.Getter;

class ResourceTile extends LandTile {
    @Getter
    private final Resource resource;
    ResourceTile(TileCoordinate c, TileNumber t, Resource r){
        super(c, t);
        this.resource = r;
    }
}