package xyz.game;

import java.util.Date;
import java.util.UUID;

public abstract class Command {
    UUID uuid = new UUID(0, 0);
    Date createdAt = new Date();
}
