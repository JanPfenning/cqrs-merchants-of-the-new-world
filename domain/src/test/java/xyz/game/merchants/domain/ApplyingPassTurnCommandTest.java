package xyz.game.merchants.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import xyz.game.merchants.domain.roll.InvalidRollException;

import java.util.LinkedList;


public class ApplyingPassTurnCommandTest {
    Player jan = new Player("Jan");
    Player lena = new Player("Lena");
    
    @Test
    public void shouldApplyPassTurnCommand() {
        Game game = new Game(0, new Player[]{jan, lena}, new LinkedList<>(), null, State.TURN);
        PassTurnCommand command = new PassTurnCommand(jan);

        int currentTurn = game.getTurn();
        
        assertDoesNotThrow(() -> game.applyCommand(command));
        
        int newTurn = game.getTurn();
        assertEquals(currentTurn + 1, newTurn);
    }

    @Test
    public void shouldFailToApplyPassTurnCommandIfItsNotTheActorsTurn() {
        Game game = new Game(0, new Player[]{jan, lena}, new LinkedList<>(), null, State.TURN);
        PassTurnCommand command = new PassTurnCommand(lena);

        int currentTurn = game.getTurn();
        
        assertThrows(UnexpectedActorException.class, () -> game.applyCommand(command));
        int newTurn = game.getTurn();
        assertEquals(currentTurn, newTurn);
    }
        
    @Test
    public void shouldFailToApplyPassTurnCommanddIfItsNotTheExpectedState() throws InvalidRollException {
        Game game = new Game(0, new Player[]{jan, lena}, new LinkedList<>(), null, State.NEW_TURN);
        PassTurnCommand command = new PassTurnCommand(jan);

        int currentTurn = game.getTurn();       
        
        assertThrows(InvalidGameStateException.class, () -> game.applyCommand(command));
        int newTurn = game.getTurn();
        assertEquals(currentTurn, newTurn);
    }

}
