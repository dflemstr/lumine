package org.lumine.command.parameter.java;

import org.lumine.command.MetaParameter;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;

public final class MetaParameterRegistry {

    public static final class Builder {
        private final ImmutableMap.Builder<Class<?>, MetaParameter<?>> builder;

        public Builder() {
            this.builder = ImmutableMap.builder();
        }

        private Builder(
                final ImmutableMap.Builder<Class<?>, MetaParameter<?>> builder) {
            this.builder = builder;
        }

        public MetaParameterRegistry build() {
            return new MetaParameterRegistry(this.builder.build());
        }

        public <A> Builder register(final MetaParameter<A> param) {
            return new Builder(this.builder.put(param.kindOfA(), param));
        }
    }

    private final ImmutableMap<Class<?>, MetaParameter<?>> kindsToParams;

    private MetaParameterRegistry(
            final ImmutableMap<Class<?>, MetaParameter<?>> kindsToParams) {
        this.kindsToParams = kindsToParams;
    }

    public <A> boolean hasParameterForKind(final Class<A> kind) {
        return this.kindsToParams.containsKey(kind);
    }

    public <A> MetaParameter<A> parameterForKind(final Class<A> kind) {

        @SuppressWarnings("unchecked")
        final// Type checked at construction
        MetaParameter<A> result = (MetaParameter<A>) this.kindsToParams
                .get(kind);
        return result;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("kindsToParams", this.kindsToParams).toString();
    }
}
