package xyz.game;

import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.game.roll.Roll;

@AllArgsConstructor
public class Game {

    @Getter
    int turn = 0;

    Player[] players;

    List<Roll> rolls = new LinkedList<>();

    private State state = State.INIT;

    public Game(Player[] players) {
        this.players = players;
    }

    public void applyCommand(PassTurnCommand command) throws UnexpectedActorException, InvalidGameStateException {
        Player currentPlayer = Game.calculateCurrentPlayer(this.turn, this.players);
        if(!command.actor.equals(currentPlayer)){
            throw new UnexpectedActorException();
        }
        if(this.state != State.TURN) {
            throw new InvalidGameStateException(State.TURN, this.state);
        }
        turn++;
    }

    public static Player calculateCurrentPlayer(int turn, Player[] players) {
        if(turn >= players.length && turn < 2*players.length)
            return players[players.length - 1 - (turn % players.length)];
        return players[turn % players.length];
    }   
}