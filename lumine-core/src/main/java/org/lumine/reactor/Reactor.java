package org.lumine.reactor;

import org.lumine.event.Event;

public interface Reactor<A extends Event> {
    public Class<A> kindOfA();

    public void react(A event);
}
