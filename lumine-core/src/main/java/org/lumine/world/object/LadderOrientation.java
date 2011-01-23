package org.lumine.world.object;

public enum LadderOrientation implements Metadata {
    EastSide((byte) 2), WestSide((byte) 3), NorthSide((byte) 4), SouthSide(
            (byte) 5);

    private final byte id;

    private LadderOrientation(final byte id) {
        this.id = id;
    }

    @Override
    public byte data() {
        return this.id;
    }
}
