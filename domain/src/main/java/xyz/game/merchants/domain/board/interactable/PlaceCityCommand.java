package xyz.game.merchants.domain.board.interactable;

import lombok.AllArgsConstructor;
import xyz.game.merchants.domain.Player;
import xyz.game.merchants.domain.board.VertexCoordinate;

@AllArgsConstructor
public class PlaceCityCommand {
    Player actor;
    VertexCoordinate destination;
}
