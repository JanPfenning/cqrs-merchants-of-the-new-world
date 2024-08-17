package xyz.game.merchants.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.game.merchants.domain.board.Coordinate;
import xyz.game.merchants.domain.board.EdgeCoordinate;
import xyz.game.merchants.domain.board.VertexCoordinate;
import xyz.game.merchants.domain.building.Building;
import xyz.game.merchants.domain.building.EdgeBuilding;
import xyz.game.merchants.domain.building.VertexBuilding;

@AllArgsConstructor
public abstract class BuildCommand {
    @Getter
    private final Player actor;
    @Getter
    private final Building building;
    @Getter
    private final Coordinate location;
}

class BuildOnVertexCommand extends BuildCommand {
    public BuildOnVertexCommand(Player actor, VertexBuilding building, VertexCoordinate location) {
        super(actor, building, location);
    }
}

class BuildOnEdgeCommand extends BuildCommand {
    public BuildOnEdgeCommand(Player actor, EdgeBuilding building, EdgeCoordinate location) {
        super(actor, building, location);
    }
}
