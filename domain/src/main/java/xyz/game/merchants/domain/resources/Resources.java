package xyz.game.merchants.domain.resources;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Resources {
    final Brick brick;
    final Lumber lumber;
    final Wool wool;
    final Grain grain;
    final Ore ore;

    public Resources(Brick brick, Lumber lumber, Wool wool, Grain grain, Ore ore) {
        this.brick = brick;
        this.lumber = lumber;
        this.wool = wool;
        this.grain = grain;
        this.ore = ore;
    }

    public Resources(int brick, int lumber, int wool, int grain, int ore) throws InvalidResourceAmountException {
        this.brick = new Brick(brick);
        this.lumber = new Lumber(lumber);
        this.wool = new Wool(wool);
        this.grain = new Grain(grain);
        this.ore = new Ore(ore);
    }

    public Resources() {
        this.brick = new Brick();
        this.lumber = new Lumber();
        this.wool = new Wool();
        this.grain = new Grain();
        this.ore = new Ore();
    }

    public boolean canAfford(Resources costs) {
        try {
            Resources _newResources = this.pay(costs);
            return true;
        } catch(InvalidResourceAmountException e) {
            return false;
        }
    }

    public Resources pay(Resources costs) throws InvalidResourceAmountException {
        return new Resources(
            brick.getAmount() - costs.brick.getAmount(),
            lumber.getAmount() - costs.lumber.getAmount(),
            wool.getAmount() - costs.wool.getAmount(),
            grain.getAmount() - costs.grain.getAmount(),
            ore.getAmount() - costs.ore.getAmount()
        );
    }

    @EqualsAndHashCode
    class Resource {
        @Getter
        private final int amount;

        Resource(int amount) throws InvalidResourceAmountException {
            if(amount < 0) { throw new InvalidResourceAmountException();}
            this.amount = amount;
        }

        Resource() {
            this.amount = 0;
        }
    }

    final class Brick extends Resource {
        Brick(int amount) throws InvalidResourceAmountException {
            super(amount);
        }
        Brick() {
            super();
        }
    }
    final class Lumber extends Resource {
        Lumber(int amount) throws InvalidResourceAmountException {
            super(amount);
        }
        Lumber() {
            super();
        }
    }
    final class Wool extends Resource {
        Wool(int amount) throws InvalidResourceAmountException {
            super(amount);
        }
        Wool() {
            super();
        }
    }
    final class Grain extends Resource {
        Grain(int amount) throws InvalidResourceAmountException {
            super(amount);
        }
        Grain() {
            super();
        }
    }
    final class Ore extends Resource {
        Ore(int amount) throws InvalidResourceAmountException {
            super(amount);
        }
        Ore() {
            super();
        }
    }
}