package org.lumine.world;

public interface Player extends HumanEntity {
    public boolean isOnline();

    public void write(String string);
}
