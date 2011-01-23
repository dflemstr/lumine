package org.lumine.command;

import org.lumine.command.parameter.RemainderParameter;
import org.lumine.command.parameter.SelectionParameter;
import org.lumine.command.parameter.ValidatedParameter;
import org.lumine.event.player.Chat;
import org.lumine.reactor.Reactor;
import org.lumine.util.Strings;
import org.lumine.util.data.Named;
import org.lumine.util.data.Remainder;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public final class CommandReactor implements Reactor<Chat> {
    private static final class ExecutionFilter implements
            Predicate<ExecutableCommand> {
        private final int depth;

        public ExecutionFilter(final int depth) {
            this.depth = depth;
        }

        @Override
        public boolean apply(ExecutableCommand input) {
            return input.segments().size() == this.depth + 1;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this).add("depth", this.depth)
                    .toString();
        }
    }

    private static final class ReductionFilter implements
            Predicate<ExecutableCommand> {
        private final int                   depth;
        private final ImmutableList<String> segments;

        public ReductionFilter(final int depth,
                final ImmutableList<String> segments) {
            this.depth = depth;
            this.segments = segments;
        }

        @Override
        public boolean apply(final ExecutableCommand input) {
            return input.segments().get(this.depth).name()
                    .equals(this.segments.get(this.depth));
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this).add("depth", this.depth)
                    .add("segments", this.segments).toString();
        }
    }

    public static CommandReactor reactingOn(
            ImmutableSet<ExecutableCommand> commands) {
        return new CommandReactor(commands);
    }

    private final ImmutableSet<ExecutableCommand> commands;

    private static final Splitter                 segmentSplitter = Splitter
                                                                          .on(CharMatcher.WHITESPACE)
                                                                          .omitEmptyStrings();

    private CommandReactor(ImmutableSet<ExecutableCommand> commands) {
        this.commands = commands;
    }

    private void handleChat(final Chat event) {
        if (event.message().startsWith("/")) {
            final String command = event.message().substring(1);
            final ImmutableList<String> segments = ImmutableList
                    .copyOf(segmentSplitter.split(command));
            this.reduceCommands(segments, this.commands, 0);
        }
    }

    @Override
    public Class<Chat> kindOfA() {
        return Chat.class;
    }

    @Override
    public void react(final Chat event) {
        this.handleChat(event);
    }

    private void reduceCommands(final ImmutableList<String> segments,
            final ImmutableSet<ExecutableCommand> commands, final int depth) {

        // TODO Too functional, not practical. Optimize!
        final Predicate<ExecutableCommand> isReductionCandidate = new ReductionFilter(
                depth, segments);

        final Predicate<ExecutableCommand> isExecutionCandidate = new ExecutionFilter(
                depth);

        final ImmutableSet<ExecutableCommand> reductionCandidates = ImmutableSet
                .copyOf(Sets.filter(commands, isReductionCandidate));

        if (reductionCandidates.isEmpty()) {
            System.err.println("Unknown command "
                    + Joiner.on(' ').join(segments));
            // TODO Report unknown command
        } else {
            ImmutableSet<ExecutableCommand> executionCandidates = ImmutableSet
                    .copyOf(Sets.filter(reductionCandidates,
                            isExecutionCandidate));

            if (executionCandidates.size() == 1) {

                final ExecutableCommand command = executionCandidates
                        .iterator().next();
                final ImmutableList.Builder<Object> paramBuilder = ImmutableList
                        .builder();
                int consumedSegments = depth + 1;
                for (int i = 0; i < command.metaParameters().size(); i++) {
                    final Named<MetaParameter<?>> namedMetaParam = command
                            .metaParameters().get(i);
                    final MetaParameter<?> metaParam = namedMetaParam.value();
                    final String stringParam = segments.get(consumedSegments);
                    if (metaParam instanceof SelectionParameter) {
                        final SelectionParameter<?> selectionParameter = (SelectionParameter<?>) metaParam;
                        final ImmutableList<?> values = selectionParameter
                                .match(stringParam);
                        if (values.isEmpty()) {
                            // TODO report unknown parameter value
                            System.err.println("Unknown parameter value for "
                                    + namedMetaParam.name());
                        } else if (values.size() > 1) {
                            // TODO report multiple alternatives
                            System.err.println("Multiple values possible for "
                                    + namedMetaParam.name() + ":");
                            for (final Object obj : values) {
                                System.err.println(obj);
                            }
                        } else {
                            assert values.get(0).getClass()
                                    .equals(metaParam.kindOfA()) : "Invalid parameter kind";
                            paramBuilder.add(values.get(0));
                        }
                        consumedSegments++;
                    } else if (metaParam instanceof ValidatedParameter) {
                        final ValidatedParameter<?> validatedParameter = (ValidatedParameter<?>) metaParam;
                        if (validatedParameter.validator().apply(stringParam)) {
                            Object value = validatedParameter.transformer()
                                    .transform(stringParam);
                            assert value.getClass().equals(metaParam.kindOfA()) : "Invalid parameter kind";
                            paramBuilder.add(value);
                        } else {
                            // TODO report invalid parameter
                            System.err.println("Unrecognized option "
                                    + stringParam + " for argument "
                                    + namedMetaParam.name());
                        }
                        consumedSegments++;
                    } else if (metaParam instanceof RemainderParameter) {
                        final String rest = Joiner.on(' ').join(
                                segments.subList(i, segments.size()));
                        paramBuilder.add(Remainder.of(rest));

                        consumedSegments = segments.size();
                        break;
                    } else {
                        // TODO better exception handling
                        System.err
                                .println("Unrecognized parameter metadata for the "
                                        + Strings.nth(i + 1)
                                        + " parameter called "
                                        + namedMetaParam.name());
                    }
                }
                if (consumedSegments == segments.size()) {
                    command.execute(paramBuilder.build());
                } else {
                    // TODO report too many arguments
                    System.err.println("Too many arguments for command.");
                }
            } else {
                this.reduceCommands(segments, reductionCandidates, depth + 1);
            }
        }
    }
}
