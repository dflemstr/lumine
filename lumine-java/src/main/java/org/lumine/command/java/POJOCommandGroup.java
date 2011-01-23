package org.lumine.command.java;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.lumine.command.CommandGroup;
import org.lumine.command.CommandSegment;
import org.lumine.command.ExecutableCommand;
import org.lumine.command.MetaParameter;
import org.lumine.command.parameter.java.MetaParameterRegistry;
import org.lumine.util.data.Named;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

/**
 * A {@link org.lumine.command.CommandGroup} implementation for Plain Old Java
 * Objects (POJOs). Each method that is annotated with
 * {@link org.lumine.command.java.Command} in the given object is exposed as a
 * command.
 */
public final class POJOCommandGroup implements CommandGroup {
    private class CommandSegmentName implements
            Function<CommandSegment, String> {

        @Override
        public String apply(final CommandSegment from) {
            return from.name();
        }

    }

    private static final class MethodCommand implements ExecutableCommand {
        private final Object                                 pojo;
        private final Method                                 method;
        private final String                                 description;
        private final ImmutableList<CommandSegment>          segments;
        private final ImmutableList<Named<MetaParameter<?>>> parameters;

        public MethodCommand(final Object pojo, final Method method,
                final ImmutableList<CommandSegment> segments,
                final MetaParameterRegistry registry)
                throws IllegalArgumentException {
            this.pojo = pojo;
            this.method = method;
            this.description = this.readDescription(method);
            this.segments = segments;
            this.parameters = this.buildParameters(method, registry);
        }

        private void addParameterForKind(
                final Class<?> kind,
                final MetaParameterRegistry registry,
                final ImmutableList.Builder<Named<MetaParameter<?>>> paramBuilder,
                final Annotation[] annotations) {
            String name = "";
            String description = "";
            for (final Annotation annotation : annotations) {
                if (annotation instanceof Parameter) {
                    name = ((Parameter) annotation).value();
                } else if (annotation instanceof Description) {
                    description = ((Description) annotation).value();
                }
            }

            if (name.isEmpty())
                // TODO handle better!
                throw new IllegalArgumentException(
                        "Missing @Param for parameter");
            else {
                final MetaParameter<?> param = registry.parameterForKind(kind);
                @SuppressWarnings("unchecked")
                final// Because Java doesn't support higher-kinded types
                Named<MetaParameter<?>> namedParam = (Named<MetaParameter<?>>) (Object) Named
                        .nameAndDescription(name, description, param);
                paramBuilder.add(namedParam);
            }
        }

        private ImmutableList<Named<MetaParameter<?>>> buildParameters(
                final Method method, final MetaParameterRegistry registry) {
            final ImmutableList.Builder<Named<MetaParameter<?>>> paramBuilder = ImmutableList
                    .builder();
            final Class<?>[] kinds = method.getParameterTypes();
            final Annotation[][] annotationField = method
                    .getParameterAnnotations();
            for (int i = 0; i < kinds.length; i++) {
                final Class<?> kind = kinds[i];
                if (registry.hasParameterForKind(kind)) {
                    this.addParameterForKind(kind, registry, paramBuilder,
                            annotationField[i]);
                }
            }
            return paramBuilder.build();
        }

        @Override
        public void execute(final ImmutableList<? extends Object> parameters)
                throws IllegalArgumentException {
            try {
                this.method.invoke(this.pojo, parameters.toArray());
            } catch (final IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (final InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        public ImmutableList<Named<MetaParameter<?>>> metaParameters() {
            return this.parameters;
        }

        private String readDescription(final Method method) {
            final Description description = method
                    .getAnnotation(Description.class);
            if (description == null)
                return "";
            else
                return description.value();
        }

        @Override
        public ImmutableList<CommandSegment> segments() {
            return this.segments;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this).add("pojo", this.pojo)
                    .add("method", this.method).add("segments", this.segments)
                    .add("parameters", this.parameters)
                    .add("description", this.description).toString();
        }
    }

    public static POJOCommandGroup of(final Object pojo,
            final MetaParameterRegistry registry) {
        return new POJOCommandGroup(pojo, registry);
    }

    private final ImmutableSet<Named<ExecutableCommand>> commands;

    private final Function<CommandSegment, String>       commandSegmentName = new CommandSegmentName();

    private POJOCommandGroup(final Object pojo,
            final MetaParameterRegistry registry) {
        final ImmutableSet.Builder<Named<ExecutableCommand>> setBuilder = ImmutableSet
                .builder();
        final ImmutableList<CommandSegment> nil = ImmutableList.of();
        this.addPojoMethodCommands(pojo, setBuilder, nil, registry);
        this.commands = setBuilder.build();
    }

    private void addPojoMethodCommands(final Object pojo,
            final ImmutableSet.Builder<Named<ExecutableCommand>> setBuilder,
            final ImmutableList<CommandSegment> initialSegments,
            final MetaParameterRegistry registry) {
        final Method[] methods = pojo.getClass().getMethods();
        for (final Method method : methods) {
            if (method.getAnnotation(Command.class) != null) {
                final Command cmd = method.getAnnotation(Command.class);
                final Description description = method
                        .getAnnotation(Description.class);
                final ImmutableList<CommandSegment> newInitialSegments = new ImmutableList.Builder<CommandSegment>()
                        .addAll(initialSegments)
                        .add(CommandSegment.named(this.firstNonEmpty(
                                cmd.value(), method.getName()))).build();
                if (method.getReturnType().equals(Void.TYPE)) {
                    final String name = Joiner.on(' ').join(
                            Lists.transform(newInitialSegments,
                                    this.commandSegmentName));
                    final MethodCommand command = new MethodCommand(pojo,
                            method, newInitialSegments, registry);
                    if (description == null) {
                        setBuilder.add(Named.name(name,
                                (ExecutableCommand) command));
                    } else {
                        setBuilder.add(Named.nameAndDescription(name,
                                description.value(),
                                (ExecutableCommand) command));
                    }
                } else if (method.getParameterTypes().length == 0) {
                    try {
                        final Object newPojo = method.invoke(pojo);
                        this.addPojoMethodCommands(newPojo, setBuilder,
                                newInitialSegments, registry);
                    } catch (final IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        assert false : "Cannot call a normal method on an object";
                    } catch (final IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (final InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public ImmutableSet<Named<ExecutableCommand>> commands() {
        return this.commands;
    }

    private String firstNonEmpty(final String a, final String b) {
        if (a.isEmpty())
            return b;
        else if (b.isEmpty())
            throw new RuntimeException("Both strings are empty.");
        else
            return a;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("commands", this.commands)
                .toString();
    }
}