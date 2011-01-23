package org.lumine.event;

import com.google.common.base.Objects;

/**
 * An event that notifies about something having been canceled. This can be that
 * an operation has failed, aborted, or any other similar event.
 */
public abstract class Cancel implements Event {
    // Foolproof singleton pattern - guaranteed single instance of Impl without
    // locks
    private static final class Impl extends Cancel {
        @Override
        public String toString() {
            return Objects.toStringHelper("Cancel").toString();
        }
    }

    private static enum Internal {
        Instance(new Impl());
        public final Cancel value;

        private Internal(final Cancel value) {
            this.value = value;
        }
    }

    /**
     * The singleton instance of {@link org.lumine.event.Cancel}.
     * 
     * @return The singleton instance of {@link org.lumine.event.Cancel}.
     */
    public static Cancel get() {
        return Internal.Instance.value;
    }

    private Cancel() {
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).toString();
    }
}
