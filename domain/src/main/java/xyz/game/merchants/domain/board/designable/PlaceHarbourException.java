package xyz.game.merchants.domain.board.designable;

public abstract class PlaceHarbourException extends Exception {}

class HarbourOnNonExistingEdgeException extends PlaceHarbourException {}
class HarbourWithoutCoastException extends PlaceHarbourException {}
