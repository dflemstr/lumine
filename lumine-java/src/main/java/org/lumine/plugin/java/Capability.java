package org.lumine.plugin.java;

import org.lumine.plugin.Session;

import com.google.common.collect.ImmutableSet;

public interface Capability {
    public void addTo(JavaPlugin plugin,
            ImmutableSet.Builder<Session> sessionBuilder);
}
