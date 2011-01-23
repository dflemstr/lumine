package org.lumine.event.java;

import org.lumine.event.Event;
import org.lumine.reactor.Emitter;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;

public class EmitterRegistry {

    public static final class Builder {
        private final ImmutableMap.Builder<Class<? extends Event>, Emitter<? extends Event>> builder;

        public Builder() {
            this.builder = ImmutableMap.builder();
        }

        private Builder(
                final ImmutableMap.Builder<Class<? extends Event>, Emitter<? extends Event>> builder) {
            this.builder = builder;
        }

        public EmitterRegistry build() {
            return new EmitterRegistry(this.builder.build());
        }

        public <A extends Event> Builder register(final Emitter<A> param) {
            return new Builder(this.builder.put(param.kindOfA(), param));
        }
    }

    private final ImmutableMap<Class<? extends Event>, Emitter<? extends Event>> eventsToEmitters;

    private EmitterRegistry(
            final ImmutableMap<Class<? extends Event>, Emitter<? extends Event>> eventsToEmitters) {
        this.eventsToEmitters = eventsToEmitters;
    }

    public <A extends Event> Emitter<A> emitterForEvent(final Class<A> event) {

        @SuppressWarnings("unchecked")
        final// Type checked at construction
        Emitter<A> result = (Emitter<A>) this.eventsToEmitters.get(event);
        return result;
    }

    public <A> boolean hasEmitterForEvent(final Class<A> event) {
        return this.eventsToEmitters.containsKey(event);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("eventsToEmitters", this.eventsToEmitters).toString();
    }
}
