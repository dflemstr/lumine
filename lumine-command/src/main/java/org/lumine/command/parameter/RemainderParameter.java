package org.lumine.command.parameter;

import org.lumine.command.MetaParameter;
import org.lumine.util.data.Remainder;

import com.google.common.base.Objects;

/**
 * Represents meta-data about the "remainder parameter" -- Used for commands
 * that accept a variable number of tokens at their ends.
 */
public abstract class RemainderParameter implements MetaParameter<Remainder> {
    // Foolproof singleton pattern - guaranteed single instance of Impl without
    // locks
    private static final class Impl extends RemainderParameter {
        @Override
        public String toString() {
            return Objects.toStringHelper("RemainderParameter").toString();
        }
    }

    private static enum Internal {
        Instance(new Impl());
        public final RemainderParameter value;

        private Internal(RemainderParameter value) {
            this.value = value;
        }
    }

    public static RemainderParameter get() {
        return Internal.Instance.value;
    }

    private RemainderParameter() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Remainder> kindOfA() {
        return Remainder.class;
    }
}
