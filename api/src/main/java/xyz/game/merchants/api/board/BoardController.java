package xyz.game.merchants.api.board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import xyz.game.domain.board.Board;
import xyz.game.domain.board.GridBoard;

@RestController("/")
@RequiredArgsConstructor
public class BoardController {
    @GetMapping("/ping")
    public String sayHello() {
        return "pong!";
    }

    @GetMapping("/board")
    public Board returnBoard() {
        GridBoard b = new GridBoard(10, 5);
        return b;
    }
}
