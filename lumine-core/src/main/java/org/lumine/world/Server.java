package org.lumine.world;

import org.lumine.plugin.PluginManager;

import com.google.common.collect.ImmutableSet;

public interface Server {
    public String name();

    public ImmutableSet<Player> players();

    public PluginManager pluginManager();

    public String version();

    public ImmutableSet<World> worlds();
}
