package xyz.game;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InvalidGameStateException extends Exception {
    InvalidGameStateException(State[] expected, State actual){
        super("expected state to be one of: '[" 
            + Arrays.stream(expected)
                .map(Enum::name)
                .collect(Collectors.joining(", "))
            + "]' but got: '" + actual.toString() + "'");
    }

    InvalidGameStateException(State expected, State actual){
        super("expected state to be: '" + expected.name() + "' but got: '" + actual.toString() + "'");
    }
}
