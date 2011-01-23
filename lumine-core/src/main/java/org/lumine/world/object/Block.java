package org.lumine.world.object;

public interface Block extends WorldObject {
    public boolean canBePlacedOn(Block block);

    public Metadata data();
}
