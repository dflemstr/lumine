package org.lumine.reactor.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.lumine.event.Event;
import org.lumine.reactor.Reactor;

public final class MethodReactor<A extends Event> implements Reactor<A> {
    public static <A extends Event> MethodReactor<A> using(final Object pojo,
            final Method method) {
        return new MethodReactor<A>(pojo, method);
    }

    private final Object pojo;

    private final Method method;

    private MethodReactor(final Object pojo, final Method method) {
        this.method = method;
        this.pojo = pojo;
        assert method.getParameterTypes().length == 1 : "Only one argument for method reactor method allowed";
    }

    @Override
    public Class<A> kindOfA() {
        @SuppressWarnings("unchecked")
        final// And you thought reflection was safe?
        Class<A> c = (Class<A>) this.method.getParameterTypes()[0];
        return c;
    }

    @Override
    public void react(final A event) {
        assert this.kindOfA().isAssignableFrom(event.getClass()) : "The event type to a MethodReactor must be the same as the method parameter type";
        try {
            this.method.invoke(this.pojo, event);
        } catch (final IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
