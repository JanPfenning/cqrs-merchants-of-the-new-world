package xyz.game.board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
    @GetMapping("/ping")
    public String sayHello() {
        return "pong!";
    }
}
