package org.lumine.command;

/**
 * Contains meta-information about a parameter for a
 * {@link org.lumine.command.ExecutableCommand}.
 * 
 * @param <A>
 *            The type of the parameter that meta-data is stored about.
 */
public interface MetaParameter<A> {
    /**
     * The kind of parameter this structure has meta-data about.
     * 
     * @return The kind of parameter this structure has meta-data about.
     */
    public Class<A> kindOfA();
}
