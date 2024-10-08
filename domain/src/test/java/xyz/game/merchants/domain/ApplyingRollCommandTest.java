package xyz.game.merchants.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import xyz.game.merchants.domain.roll.InvalidRollException;
import xyz.game.merchants.domain.roll.RollFactory;

import java.util.LinkedList;

public class ApplyingRollCommandTest {
    Player jan = new Player("Jan");
    Player lena = new Player("Lena");
    RollFactory rollFactory = new RollFactory(0);

    @Test
    public void shouldApplyRollCommandSuccessfully() throws InvalidRollException, UnexpectedActorException, InvalidGameStateException {
        RollCommand command = new RollCommand(jan, rollFactory.createStagedRoll(3, 4));
        
        Player[] players = new Player[]{jan, lena};
        Game game = new Game(0, players, new LinkedList<>(), null, State.NEW_TURN);

        assertEquals(game.rolls.size(), 0);
        
        game.applyCommand(command);
        
        assertEquals(game.rolls.get(0).getFirstD6(), 3);
        assertEquals(game.rolls.get(0).getSecondD6(), 4);
    }

    @Test
    public void shouldFailToApplyRollCommandIfItsNotTheActorsTurn() throws InvalidRollException {
        RollCommand command = new RollCommand(lena, rollFactory.createStagedRoll(3, 4));
        Player[] players = new Player[]{jan, lena};
        Game game = new Game(players, null);

        assertEquals(game.rolls.size(), 0);
        
        assertThrows(UnexpectedActorException.class, () -> game.applyCommand(command));
        
        assertEquals(game.rolls.size(), 0);
    }
    
    @Test
    public void shouldFailToApplyRollCommandIfItsNotTheExpectedState() throws InvalidRollException {
        RollCommand command = new RollCommand(jan, rollFactory.createStagedRoll(3, 4));
        Player[] players = new Player[]{jan, lena};
        Game game = new Game(0, players, new LinkedList<>(), null, State.TURN);

        assertThrows(InvalidGameStateException.class, () -> game.applyCommand(command));
    }

}
