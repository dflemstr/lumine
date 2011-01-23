package org.lumine.command;

import org.lumine.util.data.Named;

import com.google.common.collect.ImmutableList;

/**
 * An executable command.
 */
public interface ExecutableCommand {
    /**
     * Executes the command with the given parameters.
     * 
     * @param parameters
     *            Parameters matching the types of each
     *            {@link org.lumine.command.MetaParameter#kindOfA()} for each
     *            named parameter of {@link #metaParameters()}, and the length
     *            of {@link #metaParameters()}.
     * @throws IllegalArgumentException
     *             If the given parameters didn't meet the type and count
     *             requirements.
     */
    public void execute(final ImmutableList<? extends Object> parameters)
            throws IllegalArgumentException;

    /**
     * Meta data about the parameters that this command needs to run.
     * 
     * @return A list of parameter meta representations for the parameters that
     *         should be used for {@link #execute(ImmutableList)}.
     */
    public ImmutableList<Named<MetaParameter<?>>> metaParameters();

    /**
     * The determining segments identifying this command. Every command needs to
     * be unique in its overloaded segments.
     * 
     * @return The determining segments identifying this command.
     */
    public ImmutableList<CommandSegment> segments();
}
