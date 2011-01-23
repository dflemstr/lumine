package org.lumine.world.object;

public enum CoalKind implements Metadata {
    Coal((byte) 0), Charcoal((byte) 1);
    private final byte id;

    private CoalKind(final byte id) {
        this.id = id;
    }

    @Override
    public byte data() {
        return this.id;
    }
}
