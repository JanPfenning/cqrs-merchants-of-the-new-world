package xyz.game.domain.roll;

public class InvalidRollException extends Exception {

    public InvalidRollException(int firstD6, int secondD6) {
        super("first D6 '" + firstD6 + "' and/or second D6 '" + secondD6 + "' are no valid D6 values");
    }

}
