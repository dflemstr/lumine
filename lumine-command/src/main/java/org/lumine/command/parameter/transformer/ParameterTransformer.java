package org.lumine.command.parameter.transformer;

/**
 * Represents a function from a string token to a parameter value.
 * 
 * @param <A>
 *            The kind of parameter value.
 */
public interface ParameterTransformer<A> {
    /**
     * The kind of parameter this structure has meta-data about.
     * 
     * @return The kind of parameter this structure has meta-data about.
     */
    public Class<A> kindOfA();

    /**
     * Transforms the given parameter token into a parameter instance. The
     * parameter token is guaranteed to have passed some kind of validation
     * appropriate for this
     * {@link org.lumine.command.parameter.transformer.ParameterTransformer}.
     * 
     * @param param
     *            The given parameter token.
     * @return The appropriate parameter instance.
     */
    public A transform(final String param);
}
