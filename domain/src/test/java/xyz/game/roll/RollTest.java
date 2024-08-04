package xyz.game.roll;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xyz.game.InvalidRollException;

public class RollTest {
    @Test
    public void shouldCalculateSum() throws InvalidRollException{
        Roll r = new Roll(1, 2);
        assertEquals(r.getSum(), 3);
    }
}
