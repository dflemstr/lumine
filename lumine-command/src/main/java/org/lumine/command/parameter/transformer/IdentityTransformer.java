package org.lumine.command.parameter.transformer;

import com.google.common.base.Objects;

/**
 * A transformer for strings that does absolutely nothing to its input.
 */
public abstract class IdentityTransformer implements
        ParameterTransformer<String> {
    // Foolproof singleton pattern - guaranteed single instance of Impl without
    // locks
    private static final class Impl extends IdentityTransformer {
        @Override
        public String toString() {
            return Objects.toStringHelper("IdentityTransformer").toString();
        }
    }

    private static enum Internal {
        Instance(new Impl());
        public final IdentityTransformer value;

        private Internal(IdentityTransformer value) {
            this.value = value;
        }
    }

    public static IdentityTransformer get() {
        return Internal.Instance.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<String> kindOfA() {
        return String.class;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(String param) {
        assert !param.matches("\\s+") : "Parsing error: command segment mustn't contain spaces";
        return param;
    }
}
