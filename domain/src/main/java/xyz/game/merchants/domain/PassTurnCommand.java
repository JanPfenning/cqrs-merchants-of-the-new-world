package xyz.game.merchants.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PassTurnCommand extends Command {
    final Player actor;    
}
