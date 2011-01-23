package org.lumine.util.data;

import com.google.common.base.Objects;

public class Remainder {
    public static Remainder of(final String value) {
        return new Remainder(value);
    }

    private final String value;

    private Remainder(final String value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object that) {
        if (that instanceof Remainder)
            return Objects.equal(this.value, ((Remainder) that).value);
        else
            return false;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("value", this.value).toString();
    }

    public String value() {
        return this.value;
    }
}
