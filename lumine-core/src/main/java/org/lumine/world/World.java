package org.lumine.world;

import org.lumine.world.object.WorldObject;

public interface World {
    public WorldObject block(int x, int y, int z);

    public void setBlock(int x, int y, int z, WorldObject block);
}
