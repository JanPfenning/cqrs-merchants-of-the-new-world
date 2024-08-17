package xyz.game.merchants.domain;

import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.game.merchants.domain.board.EdgeCoordinate;
import xyz.game.merchants.domain.board.VertexCoordinate;
import xyz.game.merchants.domain.board.interactable.InteractableBoard;
import xyz.game.merchants.domain.board.interactable.PlaceCityCommand;
import xyz.game.merchants.domain.board.interactable.PlaceEdgeBuildingException;
import xyz.game.merchants.domain.board.interactable.PlaceRoadCommand;
import xyz.game.merchants.domain.board.interactable.PlaceSettlementCommand;
import xyz.game.merchants.domain.board.interactable.PlaceShipCommand;
import xyz.game.merchants.domain.board.interactable.PlaceVertexBuildingException;
import xyz.game.merchants.domain.building.Building;
import xyz.game.merchants.domain.building.City;
import xyz.game.merchants.domain.building.EdgeBuilding;
import xyz.game.merchants.domain.building.Road;
import xyz.game.merchants.domain.building.Settlement;
import xyz.game.merchants.domain.building.Ship;
import xyz.game.merchants.domain.resources.InvalidResourceAmountException;
import xyz.game.merchants.domain.resources.Resources;
import xyz.game.merchants.domain.roll.Roll;

@AllArgsConstructor
public class Game {

    @Getter
    int turn = 0;

    Player[] players;

    List<Roll> rolls = new LinkedList<>();

    @Getter
    private final InteractableBoard board;

    private State state = State.INIT;

    public Game(Player[] players, InteractableBoard board) {
        this.players = players;
        this.board = board;
    }

    public void applyCommand(BuildCommand command) throws UnexpectedActorException, PlaceVertexBuildingException, PlaceEdgeBuildingException, InvalidGameStateException, InitialPlacementException {
        Player currentPlayer = Game.calculateCurrentPlayer(this.turn, this.players);
        if(!command.getActor().equals(currentPlayer)){
            throw new UnexpectedActorException();
        }

        if(this.state != State.TURN && this.state != State.INIT) {
            throw new InvalidGameStateException(new State[]{State.TURN, State.INIT}, this.state);
        }

        boolean initialPlacement = turn < 2*players.length;
        if(initialPlacement) {
            validateInitialPlacementOfBuilding(command); // throws if command does not meet the additional requirements of initial placement
        }
        
        Building building = command.getBuilding();

        if(!initialPlacement) {
            Resources costs = building.getCosts();
            boolean canAfford = currentPlayer.resources.canAfford(costs);
            if(!canAfford) {
                throw new RuntimeException("player cannot afford to build this");
            }
        }
        
        if(building instanceof Settlement) {
            if(currentPlayer.pieces.settlements<1) {
                throw new RuntimeException("not enough settlements");
            }
            this.board.applyPlaceSettlementCommand(new PlaceSettlementCommand(currentPlayer, (VertexCoordinate) command.getLocation()));
            currentPlayer.pieces.settlements-=1;
        }
        if(building instanceof City) {
            if(currentPlayer.pieces.cities<1) {
                throw new RuntimeException("not enough cities");
            }
            this.board.applyPlaceCityCommand(new PlaceCityCommand(currentPlayer, (VertexCoordinate) command.getLocation()));
            currentPlayer.pieces.cities-=1;
            currentPlayer.pieces.settlements += 1;
        }
        if(building instanceof Road) {
            if(currentPlayer.pieces.roads<1) {
                throw new RuntimeException("not enough roads");
            }
            this.board.applyPlaceRoadCommand(new PlaceRoadCommand(currentPlayer, (EdgeCoordinate) command.getLocation()));
            currentPlayer.pieces.roads-=1;
        }
        if(building instanceof Ship) {
            if(currentPlayer.pieces.ships<1) {
                throw new RuntimeException("not enough ships");
            }
            this.board.applyPlaceShipCommand(new PlaceShipCommand(currentPlayer, (EdgeCoordinate) command.getLocation()));
            currentPlayer.pieces.ships-=1;
        }
        
        if(!initialPlacement) {
            try {
                currentPlayer.pay(command.getBuilding().getCosts());
            } catch (InvalidResourceAmountException e) {
                throw new RuntimeException("should have checked that actions are affordable");
            }
        }
    }
    
    private void validateInitialPlacementOfBuilding(BuildCommand command) throws InitialPlacementException {
        int expectedAmountOfSettlements = this.turn < this.players.length ? 1 : 2;
        int placedSettlementsOfPlayer = Player.Pieces.startSettlements - command.getActor().pieces.settlements;
        
        boolean expectSettlement = placedSettlementsOfPlayer < expectedAmountOfSettlements;
        if(expectSettlement) {
            boolean isSettlement = command.getBuilding() instanceof Settlement;
            if(!isSettlement) {
                throw new InitialPlacementException().new ExpectedSettlementException();
            }
        }else{
            boolean isEdgeBuilding = command.getBuilding() instanceof EdgeBuilding;
            if(!isEdgeBuilding) {
                throw new InitialPlacementException().new ExpectedRoadOrShipException();
            }
        }
    }

    public void applyCommand(PassTurnCommand command) throws UnexpectedActorException, InvalidGameStateException {
        Player currentPlayer = Game.calculateCurrentPlayer(this.turn, this.players);
        if(!command.actor.equals(currentPlayer)){
            throw new UnexpectedActorException();
        }
        // TODO also allowed in initial placement phase but only after settlement and road have been build by current player
        if(this.state != State.TURN) {
            throw new InvalidGameStateException(State.TURN, this.state);
        }
        turn++;
    }

    public void applyCommand(RollCommand command) throws UnexpectedActorException, InvalidGameStateException {
        Player currentPlayer = Game.calculateCurrentPlayer(this.turn, this.players);
        if(!command.actor.equals(currentPlayer)){
            throw new UnexpectedActorException();
        }
        if(this.state != State.NEW_TURN) {
            throw new InvalidGameStateException(State.NEW_TURN, this.state);
        }
        rolls.add(command.roll);
    }

    public static Player calculateCurrentPlayer(int turn, Player[] players) {
        if(turn >= players.length && turn < 2*players.length)
            return players[players.length - 1 - (turn % players.length)];
        return players[turn % players.length];
    }   
}