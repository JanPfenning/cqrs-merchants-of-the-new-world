package xyz.game.domain.board;

import java.util.Map;

public interface HasCosts {
    Map<Resource, Integer> getCosts(); 
}
