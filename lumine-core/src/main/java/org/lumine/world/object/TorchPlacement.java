package org.lumine.world.object;

public enum TorchPlacement implements Metadata {
    South((byte) 1), North((byte) 2), West((byte) 3), East((byte) 4), Floor(
            (byte) 5);
    private final byte id;

    private TorchPlacement(final byte id) {
        this.id = id;
    }

    @Override
    public byte data() {
        return this.id;
    }
}
