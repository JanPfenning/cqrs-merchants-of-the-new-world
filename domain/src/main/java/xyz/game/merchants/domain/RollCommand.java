package xyz.game.merchants.domain;

import lombok.AllArgsConstructor;
import xyz.game.merchants.domain.roll.Roll;

@AllArgsConstructor
public class RollCommand extends Command {
    final Player actor;
    final Roll roll;    
}
