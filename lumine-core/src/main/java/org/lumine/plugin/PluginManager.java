package org.lumine.plugin;

import com.google.common.collect.ImmutableSet;

public interface PluginManager {
    public ImmutableSet<Plugin> plugins();
}
