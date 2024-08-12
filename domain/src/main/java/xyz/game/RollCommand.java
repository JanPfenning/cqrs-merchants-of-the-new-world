package xyz.game;

import lombok.AllArgsConstructor;
import xyz.game.roll.Roll;

@AllArgsConstructor
public class RollCommand extends Command {
    final Player actor;
    final Roll roll;    
}
