package xyz.game;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.LinkedList;

import org.junit.Test;

public class ApplyingPassTurnCommandTest {
    Player jan = new Player("Jan");
    Player lena = new Player("Lena");
    
    @Test
    public void shouldApplyPassTurnCommand() {
        Game game = new Game(0, new Player[]{jan, lena}, new LinkedList<>(), State.TURN);
        PassTurnCommand command = new PassTurnCommand(jan);

        int currentTurn = game.getTurn();
        
        assertDoesNotThrow(() -> game.applyCommand(command));
        
        int newTurn = game.getTurn();
        assertEquals(currentTurn + 1, newTurn);
    }

    @Test
    public void shouldFailToApplyPassTurnCommandIfItsNotTheActorsTurn() {
        Game game = new Game(0, new Player[]{jan, lena}, new LinkedList<>(), State.TURN);
        PassTurnCommand command = new PassTurnCommand(lena);

        int currentTurn = game.getTurn();
        
        assertThrows(UnexpectedActorException.class, () -> game.applyCommand(command));
        int newTurn = game.getTurn();
        assertEquals(currentTurn, newTurn);
    }
        
    @Test
    public void shouldFailToApplyPassTurnCommanddIfItsNotTheExpectedState() throws InvalidRollException {
        Game game = new Game(0, new Player[]{jan, lena}, new LinkedList<>(), State.NEW_TURN);
        PassTurnCommand command = new PassTurnCommand(jan);

        int currentTurn = game.getTurn();       
        
        assertThrows(InvalidGameStateException.class, () -> game.applyCommand(command));
        int newTurn = game.getTurn();
        assertEquals(currentTurn, newTurn);
    }

}
