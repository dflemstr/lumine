package org.lumine.world.object;

public enum TreeKind implements Metadata {
    Pine((byte) 0), Redwood((byte) 1), Birch((byte) 2);
    private final byte id;

    private TreeKind(final byte id) {
        this.id = id;
    }

    @Override
    public byte data() {
        return this.id;
    }
}