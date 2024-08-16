package xyz.game.merchants.api.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.game.merchants.domain.board.tiles.Tile;
import xyz.game.merchants.domain.board.tiles.TileCoordinate;

@AllArgsConstructor
public class TileDTO {
    @Getter
    public final CoordinateDTO coordinate;
    @Getter
    public final TileType tileType;
    @Getter
    public final int tileNumber;

    public static TileDTO from(Tile tile) {
        TileCoordinate c = tile.getCoordinate();
        CoordinateDTO coordinateDto = new CoordinateDTO(c.getX(), c.getY());
        
        TileType tileType = TileType.from(tile.getClass().getSimpleName());
        
        int dtoNumber = tile.getNumber() != null ? tile.getNumber().getNumber() : 0;
        
        return new TileDTO(coordinateDto, tileType, dtoNumber);
    }

    enum TileType {
        WATER("WaterTile"), 
        GOLD("GoldTile"), 
        DESERT("DesertTile"), 
        BRICK("BrickTile"), 
        LUMBER("LumberTile"), 
        WOOL("WoolTile"), 
        GRAIN("GrainTile"), 
        ORE("OreTile");

        private String tileClassName;

        TileType(String tileClassName) {
            this.tileClassName = tileClassName;
        }

        @Override
        public String toString() {
            return this.tileClassName;
        }

        public static TileType from(String tileName) {
            for (TileType type : TileType.values()) {
                if (type.toString().equals(tileName)) {
                    return type;
                }
            }
            return null;
        }
    }

}