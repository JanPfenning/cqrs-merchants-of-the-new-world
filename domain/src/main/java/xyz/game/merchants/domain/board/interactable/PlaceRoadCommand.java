package xyz.game.merchants.domain.board.interactable;

import lombok.AllArgsConstructor;
import xyz.game.merchants.domain.Player;
import xyz.game.merchants.domain.board.EdgeCoordinate;

@AllArgsConstructor
public class PlaceRoadCommand {
    Player actor;
    EdgeCoordinate destination;
}
