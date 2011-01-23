package org.lumine.command.parameter;

import org.lumine.command.MetaParameter;

import com.google.common.collect.ImmutableList;

/**
 * Represents meta-data about a parameter that has a finite number of
 * alternatives.
 * 
 * @param <A>
 *            The type of the parameter that meta-data is stored about.
 */
public interface SelectionParameter<A> extends MetaParameter<A> {
    /**
     * Parses a given string and generates a list of alternative parameter
     * instances that might match the string.
     * 
     * @param param
     *            A token string, mustn't contain spaces.
     * @return A list of alternative parameter instances that might match the
     *         string, or {@see com.google.common.collect.ImmutableList#of()} if
     *         there were no matches.
     */
    public ImmutableList<A> match(final String param);
}
