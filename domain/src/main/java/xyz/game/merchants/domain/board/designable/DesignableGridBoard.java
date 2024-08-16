package xyz.game.merchants.domain.board.designable;

import java.util.Arrays;
import java.util.Map;

import xyz.game.merchants.domain.board.GridBoard;
import xyz.game.merchants.domain.board.tiles.LandTile;
import xyz.game.merchants.domain.board.tiles.Tile;
import xyz.game.merchants.domain.board.tiles.TileCoordinate;
import xyz.game.merchants.domain.board.tiles.WaterTile;

public class DesignableGridBoard extends GridBoard implements DesignableBoard {

    public DesignableGridBoard(int width, int height, Map<TileCoordinate, Tile> tiles) {
        this(width, height);

        for (Map.Entry<TileCoordinate, Tile> entry : tiles.entrySet()) {
            if(tiles.get(entry.getKey()) == null) throw new RuntimeException("tile coordinate "+entry.getKey().toString()+" is out of bounds");
            this.tiles.put(entry.getKey(), entry.getValue());
        }
    }

    public DesignableGridBoard(int width, int height) {
        super(width, height);
    }

    @Override
    public void applySetTileCommand(SetTileCommand command) throws InvalidTileCoordinateException {
        Tile t = this.getTile(command.tile.getCoordinate());
        if(t == null) {
            throw new InvalidTileCoordinateException();
        }
        if(t instanceof WaterTile && command.tile instanceof LandTile || t instanceof LandTile && command.tile instanceof WaterTile) {
            Arrays.stream(command.tile.getCoordinate().getSurroundingEdgeCoordinates()).forEach(edgeCoordinate -> this.harbours.remove(edgeCoordinate));
        }
        this.tiles.put(command.tile.getCoordinate(), command.tile);
    }

    @Override
    public void applyPlaceHarbourCommand(PlaceHarbourCommand command) throws PlaceHarbourException {
        if(this.getEdge(command.getLocation()) == null) {
            throw new HarbourOnNonExistingEdgeException();
        }
        TileCoordinate[] adjTileCoordinates = command.getLocation().getSurroundingTileCoordinates();
        boolean eitherIsLand = getTile(adjTileCoordinates[0]) instanceof LandTile || getTile(adjTileCoordinates[1]) instanceof LandTile;
        boolean eitherIsWater = getTile(adjTileCoordinates[0]) instanceof WaterTile || getTile(adjTileCoordinates[1]) instanceof WaterTile;
        boolean edgeIsCoast = eitherIsLand && eitherIsWater;
        if(!edgeIsCoast) {
            throw new HarbourWithoutCoastException();
        }
        Arrays.stream(command.getLocation().getSurroundingEdgeCoordinates()).forEach(edgeC -> this.harbours.remove(edgeC));
        this.harbours.put(command.getLocation(), command.getHarbour());
    }

    @Override
    public void applyRemoveHarbourCommand(RemoveHarbourCommand command) {
        this.harbours.remove(command.getTarget());
    }
}
