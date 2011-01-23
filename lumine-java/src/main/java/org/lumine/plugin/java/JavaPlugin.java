package org.lumine.plugin.java;

import org.lumine.plugin.Plugin;
import org.lumine.plugin.Session;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public abstract class JavaPlugin implements Plugin {
    private final ImmutableSet<Session> sessions;

    protected JavaPlugin() {
        final ImmutableSet.Builder<Session> sessionBuilder = ImmutableSet
                .builder();
        for (final Capability capability : this.capabilities()) {
            capability.addTo(this, sessionBuilder);
        }
        this.sessions = sessionBuilder.build();
    }

    public abstract ImmutableList<Capability> capabilities();

    @Override
    public ImmutableSet<Session> sessions() {
        return this.sessions;
    }
}
