package org.lumine.world;

public final class ItemStack {
    private final int      amount;
    private final Material type;

    private ItemStack(final Material type, final int amount) {
        this.amount = amount;
        this.type = type;
    }

    public int amount() {
        return this.amount;
    }

    public Material type() {
        return this.type;
    }
}
