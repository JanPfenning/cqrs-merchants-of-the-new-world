package xyz.game.merchants.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import xyz.game.merchants.domain.resources.InvalidResourceAmountException;
import xyz.game.merchants.domain.resources.Resources;

@AllArgsConstructor
public class Player {
    public final UUID uuid;
    public final String name;
    public Resources resources = new Resources();
    public Pieces pieces = new Pieces();
    
    public Player(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

    class Pieces {
        static int startSettlements = 6;
        static int startCities = 4;
        static int startRoads = 20;
        static int startShips = 12;

        int settlements = Pieces.startSettlements;
        int cities = Pieces.startCities;
        int roads = Pieces.startRoads;
        int ships = Pieces.startShips;

        Pieces(int startSettlements, int startCities, int startRoads, int startShips) {
            this.settlements = startSettlements;
            this.cities = startCities;
            this.roads = startRoads;
            this.ships = startShips;
        }

        Pieces() {}
    }

    public void pay(Resources costs) throws InvalidResourceAmountException {
        this.resources = this.resources.pay(costs);
    }
}
