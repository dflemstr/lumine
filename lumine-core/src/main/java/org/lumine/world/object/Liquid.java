package org.lumine.world.object;

public class Liquid implements Metadata {
    private final boolean falling;
    private final byte    filledAmount;
    private final byte    maxFilledAmount;

    private Liquid(final boolean falling, final byte filledAmount,
            final byte maxFilledAmount) {
        this.falling = falling;
        this.filledAmount = filledAmount;
        this.maxFilledAmount = maxFilledAmount;
    }

    @Override
    public byte data() {
        return (byte) (this.filledAmount | (this.falling ? 8 : 0));
    }

    public boolean falling() {
        return this.falling;
    }

    public byte filledAmount() {
        return this.filledAmount;
    }

    public byte maxFilledAmount() {
        return this.maxFilledAmount;
    }

    public Liquid with(final boolean falling, final byte filledAmount,
            final byte maxFilledAmount) {
        return new Liquid(falling, filledAmount, maxFilledAmount);
    }
}
