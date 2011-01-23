package org.lumine.event;

import com.google.common.base.Objects;

/**
 * An event that notifies about acknowledgment. This can be that an operation
 * has succeeded, or any other similar event.
 */
public abstract class Acknowledge implements Event {
    // Foolproof singleton pattern - guaranteed single instance of Impl without
    // locks
    private static final class Impl extends Acknowledge {
        @Override
        public String toString() {
            return Objects.toStringHelper("Acknowledge").toString();
        }
    }

    private static enum Internal {
        Instance(new Impl());
        public final Acknowledge value;

        private Internal(final Acknowledge value) {
            this.value = value;
        }
    }

    /**
     * The singleton instance of {@link org.lumine.event.Acknowledge}.
     * 
     * @return The singleton instance of {@link org.lumine.event.Acknowledge}.
     */
    public static Acknowledge get() {
        return Internal.Instance.value;
    }

    private Acknowledge() {
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).toString();
    }
}
