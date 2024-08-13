package xyz.game.merchants.api.board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.game.merchants.domain.board.GridBoard;

@RestController("/")
@RequiredArgsConstructor
public class BoardController {
    @GetMapping("/ping")
    public String sayHello() {
        return "pong!";
    }

    @GetMapping("/board")
    public BoardDTO returnBoard() {
        GridBoard b = new GridBoard(10, 5);

        BoardDTO dto = BoardDTO.from(b);
        
        return dto;
    }
}
