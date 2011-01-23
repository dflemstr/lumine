package org.lumine.world.object;

public final class Age implements Metadata {

    public static Age of(final byte age, final byte maxAge) {
        return new Age(age, maxAge);
    }

    private final byte age;
    private final byte maxAge;

    private Age(final byte age, final byte maxAge) {
        this.age = age;
        this.maxAge = maxAge;
    }

    public byte age() {
        return this.age;
    }

    @Override
    public byte data() {
        return this.age;
    }

    public byte maxAge() {
        return this.maxAge;
    }
}
