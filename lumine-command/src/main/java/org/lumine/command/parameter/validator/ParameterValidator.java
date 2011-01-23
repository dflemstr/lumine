package org.lumine.command.parameter.validator;

import com.google.common.base.Predicate;

/**
 * A predicate that checks whether a string is valid for some kind of parameter
 * transformation.
 */
public interface ParameterValidator extends Predicate<String> {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean apply(final String param);
}
