package xyz.game.domain.roll;

import java.util.Random;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Roll {
    @Getter
    private final int firstD6;
    @Getter
    private final int secondD6;

    Roll(Random random) {
        this.firstD6 = random.nextInt(6) + 1;
        this.secondD6 = random.nextInt(6) + 1;
    }

    Roll(int firstD6, int secondD6) throws InvalidRollException {
        if(firstD6 < 1 || firstD6 > 6 || secondD6 < 1 || secondD6 > 6)
            throw new InvalidRollException(firstD6, secondD6);
        this.firstD6 = firstD6;
        this.secondD6 = secondD6;
    }

    public int getSum() {
        return firstD6 + secondD6;
    }

}
