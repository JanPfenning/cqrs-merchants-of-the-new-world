package xyz.game.domain.roll;

import java.util.Random;

public class RollFactory {
    private final Random random;

    public RollFactory(long seed) {
        this.random = new Random(seed);
    }

    public Roll createRoll() {
        return new Roll(this.random);
    }    

    public Roll createStagedRoll(int firstD6, int secondD6) throws InvalidRollException {
        return new Roll(firstD6, secondD6);
    }
}
