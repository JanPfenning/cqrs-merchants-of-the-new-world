package xyz.game.merchants.domain.board;

import lombok.Value;

@Value
public class TileNumber {
    int number;
    TileNumber(int number) throws InvalidTileNumberException {
        if(number < 2 || number == 7 || number > 12){
            throw new InvalidTileNumberException(number);
        }
        this.number = number;
    }

    public class InvalidTileNumberException extends Exception {
        InvalidTileNumberException(int number){
            super(number + " is not a valid TileNumber");
        }
    }    
}
