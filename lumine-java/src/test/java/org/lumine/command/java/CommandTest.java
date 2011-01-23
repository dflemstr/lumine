package org.lumine.command.java;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.lumine.command.CommandReactor;
import org.lumine.command.ExecutableCommand;
import org.lumine.command.parameter.RemainderParameter;
import org.lumine.command.parameter.ValidatedParameter;
import org.lumine.command.parameter.java.MetaParameterRegistry;
import org.lumine.command.parameter.transformer.IdentityTransformer;
import org.lumine.command.parameter.validator.PermissiveValidator;
import org.lumine.event.player.Chat;
import org.lumine.util.data.Named;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

public class CommandTest extends TestCase {
    private MetaParameterRegistry                                        registry;
    private final ExamplePOJO                                            pojo   = new ExamplePOJO();

    private static class UnNamer implements Function<Named<ExecutableCommand>, ExecutableCommand> {
        @Override
        public ExecutableCommand apply(
                final Named<ExecutableCommand> from) {
            return from
                    .value();
        }
    }
    
    private static UnNamer unName = new UnNamer();

    private void runCommand(final CommandReactor reactor, final String command) {
        reactor.react(Chat.saying(command, null));
        Assert.assertTrue(this.pojo.commandHandled());
        this.pojo.reset();
    }

    @Override
    protected void setUp() {
        this.registry = new MetaParameterRegistry.Builder()
                .register(
                        ValidatedParameter.with(PermissiveValidator.get(),
                                IdentityTransformer.get()))
                .register(RemainderParameter.get()).build();
    }

    public void testCommandDetection() {
        final POJOCommandGroup group = POJOCommandGroup.of(this.pojo,
                this.registry);
        for (final Named<ExecutableCommand> command : group.commands()) {
            System.out.println("Name: " + command.name());
            System.out.println("Description: " + command.description());
            System.out.print("Result: ");
            if (command.name().equals("simple")) {
                command.value().execute(ImmutableList.of());
            } else if (command.name().equals("tp")) {
                command.value().execute(ImmutableList.of("player1", "player2"));
            } else if (command.name().equals("rtp")) {
                command.value().execute(ImmutableList.of("player1", "player2"));
            } else if (command.name().equals("modify users")) {
                command.value().execute(ImmutableList.of());
            } else if (command.name().equals("modify world")) {
                command.value().execute(ImmutableList.of("far_away"));
            }
            System.out.println();

            Assert.assertTrue(this.pojo.commandHandled());
            this.pojo.reset();
        }
    }

    public void testCommandParsing() {
        final POJOCommandGroup group = POJOCommandGroup.of(this.pojo,
                this.registry);
        final CommandReactor reactor = CommandReactor.reactingOn(ImmutableSet
                .copyOf(Iterables.transform(group.commands(),
                        CommandTest.unName)));
        this.runCommand(reactor, "/tp player1 player2");
        this.runCommand(reactor, "/tp    player1       player2");
        this.runCommand(reactor, "/rtp player1 player2");
        this.runCommand(reactor, "/modify users");
        this.runCommand(reactor, "/modify world far_away");
    }
}
