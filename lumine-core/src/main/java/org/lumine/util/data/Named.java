package org.lumine.util.data;

import com.google.common.base.Objects;

public final class Named<A> {
    public static <A> Named<A> name(final String name, final A value) {
        return new Named<A>(name, "", value);
    }

    public static <A> Named<A> nameAndDescription(final String name,
            final String description, final A value) {
        return new Named<A>(name, description, value);
    }

    private final String name;

    private final String description;

    private final A      value;

    private Named(final String name, final String description, final A value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public String description() {
        return this.description;
    }

    public String name() {
        return this.name;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name)
                .add("description", this.description).add("value", this.value)
                .toString();
    }

    public A value() {
        return this.value;
    }
}
