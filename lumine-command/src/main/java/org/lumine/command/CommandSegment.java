package org.lumine.command;

import com.google.common.base.Objects;

/**
 * A segment that identifies a command. A command consists of one or multiple
 * initial segments followed by parameters.
 */
public final class CommandSegment {
    public static CommandSegment named(final String name) {
        return new CommandSegment(name);
    }

    private final String name;

    private CommandSegment(final String name) {
        assert name.matches("\\w+") : "Command segment mustn't contain whitespace.";
        this.name = name;
    }

    /**
     * This segment's name.
     * 
     * @return This segment's name.
     */
    public String name() {
        return this.name;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name).toString();
    }
}
