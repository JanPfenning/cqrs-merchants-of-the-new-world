package xyz.game.merchants.domain.board.designable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.game.merchants.domain.board.EdgeCoordinate;

@AllArgsConstructor
public class RemoveHarbourCommand {
    @Getter
    private final EdgeCoordinate target;
}
