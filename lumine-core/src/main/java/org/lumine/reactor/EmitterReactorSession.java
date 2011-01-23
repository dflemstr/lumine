package org.lumine.reactor;

import org.lumine.event.Event;
import org.lumine.plugin.Session;

public final class EmitterReactorSession<A extends Event> implements Session {

    public static <A extends Event> EmitterReactorSession<A> with(
            final Reactor<A> reactor, final Emitter<A> emitter) {
        return new EmitterReactorSession<A>(reactor, emitter);
    }

    private final Reactor<A> reactor;

    private final Emitter<A> emitter;

    private EmitterReactorSession(final Reactor<A> reactor,
            final Emitter<A> emitter) {
        this.reactor = reactor;
        this.emitter = emitter;
    }

    @Override
    public void end() {
        this.emitter.removeReactor(this.reactor);
    }

    @Override
    public void start() {
        this.emitter.addReactor(this.reactor);
    }

}
