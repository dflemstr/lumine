package org.lumine.event.player;

import org.lumine.event.Event;
import org.lumine.world.Player;

import com.google.common.base.Objects;

public final class Chat implements Event {
    public static Chat saying(final String message, final Player player) {
        return new Chat(message, player);
    }

    private final String message;
    private final Player player;

    private Chat(final String message, final Player player) {
        this.message = message;
        this.player = player;
    }

    public String message() {
        return this.message;
    }

    public Player player() {
        return this.player;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("message", this.message)
                .add("player", this.player).toString();
    }
}
