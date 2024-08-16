package xyz.game.merchants.domain.board.designable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.game.merchants.domain.board.EdgeCoordinate;
import xyz.game.merchants.domain.board.harbours.Harbour;

@AllArgsConstructor
public class PlaceHarbourCommand {
    @Getter
    private final EdgeCoordinate location;
    @Getter
    private final Harbour harbour;
}
