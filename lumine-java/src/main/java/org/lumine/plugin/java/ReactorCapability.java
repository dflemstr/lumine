package org.lumine.plugin.java;

import java.lang.reflect.Method;

import org.lumine.event.Event;
import org.lumine.event.java.EmitterRegistry;
import org.lumine.plugin.Session;
import org.lumine.reactor.Emitter;
import org.lumine.reactor.EmitterReactorSession;
import org.lumine.reactor.Reactor;
import org.lumine.reactor.java.MethodReactor;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet.Builder;

public abstract class ReactorCapability implements Capability {
    private static final class Impl extends ReactorCapability {
    }

    private static enum Internal {
        Instance(new Impl());
        public final ReactorCapability value;

        private Internal(final ReactorCapability value) {
            this.value = value;
        }
    }

    private ReactorCapability() {
    }
    private <A extends Event> void addMethodTo(final Method method,
            final JavaPlugin plugin, final Class<A> eventClass,
            final Builder<Session> sessionBuilder,
            final EmitterRegistry registry) {
        final Reactor<A> reactor = MethodReactor.using(plugin, method);
        final Emitter<A> emitter = registry.emitterForEvent(eventClass);
        sessionBuilder.add(EmitterReactorSession.with(reactor, emitter));
    }

    @Override
    public void addTo(final JavaPlugin plugin,
            final Builder<Session> sessionBuilder) {
        // TODO add master registry
        final EmitterRegistry registry = new EmitterRegistry.Builder()
                .build();
        for (final Method method : plugin.getClass().getMethods()) {
            final React reactAnnot = method.getAnnotation(React.class);
            if (reactAnnot != null) {
                if (method.getParameterTypes().length != 1)
                    throw new RuntimeException(
                            "Method has the wrong number of parameter types, must be 1");
                if (Event.class.isAssignableFrom(
                        method.getParameterTypes()[0]))
                    throw new RuntimeException(
                            "The marked method doesn't have a parameter that implements Event");
                if (!registry.hasEmitterForEvent(method.getParameterTypes()[0]))
                    throw new RuntimeException(
                            "@React used with an unregistered event type");
                @SuppressWarnings("unchecked")
                //We have checked the precondition for the cast above.
                Class<? extends Event> eventClass = (Class<? extends Event>)method.getParameterTypes()[0];
                this.addMethodTo(method, plugin, eventClass,
                        sessionBuilder, registry);
            }
        }
    }

    @Override
    public String toString() {
        return Objects.toStringHelper("ReactorCapability").toString();
    }

    public static ReactorCapability get() {
        return Internal.Instance.value;
    }
}
