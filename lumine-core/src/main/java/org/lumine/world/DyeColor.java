package org.lumine.world;

public enum DyeColor {
    White(0, "White"), Orange(1, "Orange"), Magenta(2, "Magenta"), LightBlue(3,
            "Light blue"), Yellow(4, "Yellow"), LightGreen(5, "Light green"), Pink(
            6, "Pink"), Gray(7, "Gray"), LightGray(8, "Light gray"), Cyan(9,
            "Cyan"), Purple(10, "Purple"), Blue(11, "Blue"), Brown(12, "Brown"), DarkGreen(
            13, "Dark green"), Red(14, "Red"), Black(15, "Black");
    private final int    id;
    private final String name;

    private DyeColor(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String dyeName() {
        return this.name;
    }

    public int id() {
        return this.id;
    }
}
