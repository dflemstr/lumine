package org.lumine.world.object;

public enum StairOrientation implements Metadata {
    AscendingSouth((byte) 0), AscendingNorth((byte) 1), AscendingWest((byte) 2), AscendingEast(
            (byte) 3);

    private final byte id;

    private StairOrientation(final byte id) {
        this.id = id;
    }

    @Override
    public byte data() {
        return this.id;
    }

}
