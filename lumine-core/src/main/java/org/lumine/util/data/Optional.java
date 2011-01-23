package org.lumine.util.data;

public abstract class Optional<A> {
    private static class Just<A> extends Optional<A> {
        private final A value;

        public Just(final A value) {
            this.value = value;
        }

        @Override
        public boolean isDefined() {
            return true;
        }

        @Override
        public boolean isNothing() {
            return false;
        }

        @Override
        public A value() {
            return this.value;
        }

        @Override
        public A valueOrElse(final A alternative) {
            return this.value;
        }
    }

    private static class Nothing extends Optional<Object> {

        @Override
        public boolean isDefined() {
            return false;
        }

        @Override
        public boolean isNothing() {
            return true;
        }

        @Override
        public Object value() {
            throw new RuntimeException("Called value() on Maybe.nothing.");
        }

        @Override
        public Object valueOrElse(final Object alternative) {
            return alternative;
        }

    }

    private final static Nothing nothing = new Nothing();

    public static <A> Optional<A> just(final A value) {
        return new Just<A>(value);
    }

    public static <A> Optional<A> nothing() {
        @SuppressWarnings("unchecked")
        final// Variance and stuff
        Optional<A> result = (Optional<A>) Optional.nothing;
        return result;
    }

    private Optional() {
    }

    public abstract boolean isDefined();

    public abstract boolean isNothing();

    public abstract A value();

    public abstract A valueOrElse(A alternative);
}
