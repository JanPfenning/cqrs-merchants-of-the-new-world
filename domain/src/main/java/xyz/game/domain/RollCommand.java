package xyz.game.domain;

import lombok.AllArgsConstructor;
import xyz.game.domain.roll.Roll;

@AllArgsConstructor
public class RollCommand extends Command {
    final Player actor;
    final Roll roll;    
}
