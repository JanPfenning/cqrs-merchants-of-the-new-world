package xyz.game.merchants.domain;

public enum State {
    INIT,
    INIT_PLACE_SETTLE_FORWARD,
    INIT_PLACE_EDGE_FORWARD,
    INIT_PLACE_SETTLE_BACKWARD,
    INIT_PLACE_EDGE_BACKWARD,
    NEW_TURN,
    TURN
}
