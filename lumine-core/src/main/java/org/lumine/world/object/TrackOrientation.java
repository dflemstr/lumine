package org.lumine.world.object;

public enum TrackOrientation implements Metadata {
    EastWest((byte) 0), NorthSouth((byte) 1), AscendSouth((byte) 2), AscendNorth(
            (byte) 3), AscendEast((byte) 4), AscendWest((byte) 5), NorthEastCorner(
            (byte) 6), SouthEastCorner((byte) 7), SouthWestCorner((byte) 8), NorthWestCorner(
            (byte) 9);
    private final byte id;

    private TrackOrientation(final byte id) {
        this.id = id;
    }

    @Override
    public byte data() {
        return this.id;
    }
}
