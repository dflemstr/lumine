package org.lumine.reactor;

import org.lumine.event.Event;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public final class Emitter<A extends Event> {
    public static <A extends Event> Emitter<A> ofKind(final Class<A> kindOfA) {
        @SuppressWarnings("unchecked")
        final// And you thought that Java had a good generic type system?
        ImmutableSet<Reactor<? super A>> nil = (ImmutableSet<Reactor<? super A>>) (Object) ImmutableSet
                .of();
        return new Emitter<A>(nil, kindOfA);
    }

    public static <A extends Event> Emitter<A> withInitialReactors(
            final ImmutableSet<Reactor<? super A>> reactors,
            final Class<A> kindOfA) {
        return new Emitter<A>(reactors, kindOfA);
    }

    private ImmutableSet<Reactor<? super A>> reactors;

    private final Class<A>                   kindOfA;

    private Emitter(final ImmutableSet<Reactor<? super A>> reactors,
            final Class<A> kindOfA) {
        this.reactors = reactors;
        this.kindOfA = kindOfA;
    }

    public void addReactor(final Reactor<? super A> reactor) {
        this.reactors = new ImmutableSet.Builder<Reactor<? super A>>()
                .addAll(this.reactors).add(reactor).build();
    }

    public void addReactors(final ImmutableSet<Reactor<? super A>> newReactors) {
        this.reactors = ImmutableSet.copyOf(Sets.union(this.reactors,
                newReactors));
    }

    public void emit(final A a) {
        for (final Reactor<? super A> reactor : this.reactors) {
            reactor.react(a);
        }
    }

    public Class<A> kindOfA() {
        return this.kindOfA;
    }

    public void removeReactor(final Reactor<? super A> reactor) {
        @SuppressWarnings("unchecked")
        final// Because dependent types don't exist in Java
        Predicate<Reactor<? super A>> predicate = (Predicate<Reactor<? super A>>) (Object) Predicates
                .not(Predicates.equalTo(reactor));
        this.reactors = ImmutableSet.copyOf(Sets.filter(this.reactors,
                predicate));
    }

    public void removeReactors(
            final ImmutableSet<Reactor<? super A>> oldReactors) {
        this.reactors = ImmutableSet.copyOf(Sets.difference(this.reactors,
                oldReactors));
    }
}
