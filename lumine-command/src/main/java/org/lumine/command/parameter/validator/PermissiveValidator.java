package org.lumine.command.parameter.validator;

import com.google.common.base.Objects;

/**
 * A parameter validator that accepts any string.
 */
public abstract class PermissiveValidator implements ParameterValidator {

    // Foolproof singleton pattern - guaranteed single instance of Impl without
    // locks
    private static final class Impl extends PermissiveValidator {
        @Override
        public String toString() {
            return Objects.toStringHelper("PermissiveValidator").toString();
        }
    }

    private static enum Internal {
        Instance(new Impl());
        public final PermissiveValidator value;

        private Internal(PermissiveValidator value) {
            this.value = value;
        }
    }

    public static PermissiveValidator get() {
        return Internal.Instance.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean apply(final String param) {
        return true;
    }
}
