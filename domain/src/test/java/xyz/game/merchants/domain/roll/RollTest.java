package xyz.game.merchants.domain.roll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RollTest {
    @Test
    public void shouldCalculateSum() throws InvalidRollException{
        Roll r = new Roll(1, 2);
        assertEquals(r.getSum(), 3);
    }
}
