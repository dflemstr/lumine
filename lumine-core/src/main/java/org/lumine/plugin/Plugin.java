package org.lumine.plugin;

import com.google.common.collect.ImmutableSet;

public interface Plugin {
    public ImmutableSet<Session> sessions();
}
