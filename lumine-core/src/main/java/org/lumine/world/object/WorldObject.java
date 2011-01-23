package org.lumine.world.object;

import org.lumine.world.Material;

import com.google.common.collect.ImmutableSet;

public interface WorldObject {
    public ImmutableSet<String> alternativeNames();

    public Material material();

    public String preferredName();
}
