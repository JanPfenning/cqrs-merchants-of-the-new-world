package xyz.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InvalidGameStateExecptionTest {
    @Test
    public void shouldThrowWithExpectedMessageForMultipeAccpetedStates(){
        InvalidGameStateException ex = new InvalidGameStateException(new State[]{State.INIT, State.NEW_TURN}, State.TURN);
        assertEquals(ex.getMessage(), "expected state to be one of: '[INIT, NEW_TURN]' but got: 'TURN'");
    }

    @Test
    public void shouldThrowWithExpectedMessageForSingleAccpetedStates(){
        InvalidGameStateException ex = new InvalidGameStateException(State.INIT, State.TURN);
        assertEquals(ex.getMessage(), "expected state to be: 'INIT' but got: 'TURN'");
    }
}
