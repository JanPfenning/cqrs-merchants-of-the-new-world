package xyz.game.merchants.domain;

public class InitialPlacementException extends BuildException {

    class ExpectedSettlementException extends InitialPlacementException{} 
    class ExpectedRoadOrShipException extends InitialPlacementException{} 
    class LeftSettlementWithoutConnectionException extends InitialPlacementException{} 
}
