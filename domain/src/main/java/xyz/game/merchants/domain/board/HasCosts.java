package xyz.game.merchants.domain.board;

import java.util.Map;

public interface HasCosts {
    Map<Resource, Integer> getCosts(); 
}
