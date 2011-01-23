package org.lumine.world.object;

public interface Item extends WorldObject {
    public int maxStackSize();

    public boolean stackable();
}
