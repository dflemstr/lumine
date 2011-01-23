package org.lumine.command;

import org.lumine.util.data.Named;

import com.google.common.collect.ImmutableSet;

/**
 * A group of {@link org.sparkmod.command.MetaCommand}s.
 */
public interface CommandGroup {
    /**
     * The actual group of {@link org.sparkmod.command.MetaCommand}s.
     * 
     * @return The actual group of {@link org.sparkmod.command.MetaCommand}s.
     */
    public ImmutableSet<Named<ExecutableCommand>> commands();
}
