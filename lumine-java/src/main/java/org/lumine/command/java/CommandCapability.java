package org.lumine.command.java;

import org.lumine.command.CommandReactor;
import org.lumine.command.ExecutableCommand;
import org.lumine.command.parameter.java.MetaParameterRegistry;
import org.lumine.plugin.Session;
import org.lumine.plugin.java.Capability;
import org.lumine.plugin.java.JavaPlugin;
import org.lumine.reactor.EmitterReactorSession;
import org.lumine.util.data.Named;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Iterables;

public abstract class CommandCapability implements Capability {

    private static class Impl extends CommandCapability {
    }

    private static enum Internal {
        Instance(new Impl());
        public final CommandCapability value;

        private Internal(final CommandCapability value) {
            this.value = value;
        }
    }

    private static class UnNamer implements
            Function<Named<ExecutableCommand>, ExecutableCommand> {
        @Override
        public ExecutableCommand apply(final Named<ExecutableCommand> from) {
            return from.value();
        }
    }

    private static UnNamer unName = new UnNamer();

    private CommandCapability() {
    }

    @Override
    public void addTo(final JavaPlugin plugin,
            final Builder<Session> sessionBuilder) {
        // TODO add master registry
        final POJOCommandGroup group = POJOCommandGroup.of(plugin,
                new MetaParameterRegistry.Builder().build());
        final CommandReactor reactor = CommandReactor.reactingOn(ImmutableSet
                .copyOf(Iterables.transform(group.commands(),
                        CommandCapability.unName)));
        // TODO add chat message emitter
        sessionBuilder.add(EmitterReactorSession.with(reactor, null));
    }

    public static CommandCapability get() {
        return Internal.Instance.value;
    }
}
