package org.lumine.command.parameter;

import org.lumine.command.MetaParameter;
import org.lumine.command.parameter.transformer.ParameterTransformer;
import org.lumine.command.parameter.validator.ParameterValidator;

import com.google.common.base.Objects;

/**
 * Represents meta-data for a parameter that is validated using a validator.
 * 
 * @param <A>
 *            The type of the parameter that meta-data is stored about.
 */
public final class ValidatedParameter<A> implements MetaParameter<A> {

    public static <A> ValidatedParameter<A> with(
            final ParameterValidator validator,
            final ParameterTransformer<A> transformer) {
        return new ValidatedParameter<A>(validator, transformer);
    }

    private final ParameterValidator      validator;

    private final ParameterTransformer<A> transformer;

    private ValidatedParameter(final ParameterValidator validator,
            final ParameterTransformer<A> transformer) {
        this.validator = validator;
        this.transformer = transformer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<A> kindOfA() {
        return this.transformer.kindOfA();
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("validator", this.validator)
                .add("transformer", this.transformer).toString();
    }

    /**
     * The transformer that transforms a validated string into an actual
     * parameter instance
     * 
     * @return The transformer that transforms a validated string into an actual
     *         parameter instance
     */
    public ParameterTransformer<A> transformer() {
        return this.transformer;
    }

    /**
     * The validator that checks if a given token string is valid for this kind
     * of {@link org.lumine.command.parameter.ValidatedParameter}.
     * 
     * @return The validator that checks if a given token string is valid for
     *         this kind of
     *         {@link org.lumine.command.parameter.ValidatedParameter}.
     */
    public ParameterValidator validator() {
        return this.validator;
    }
}
