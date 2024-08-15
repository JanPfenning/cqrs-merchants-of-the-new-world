package xyz.game.merchants.domain.board.designable;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import xyz.game.merchants.domain.board.Tile;

@AllArgsConstructor
public class SetTileCommand {
    @NonNull Tile tile;
}
