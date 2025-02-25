package com.xplug.tech.enums;

public enum RateUnit {

    PER_GRAM("per gram", "g"),
    PER_KG("per kilogram", "kg"),
    PER_LITER("per litre", "L"),
    PER_MILLILITER("per milliliter", "mL"),
    PER_TON("per ton", "t"),

    // Area-Based Units (for application rates)
    PER_SQUARE_METER("Per Square Meter", "m²"),
    PER_HECTARE("Per Hectare", "ha");

    private final String description;
    private final String symbol;

    RateUnit(String description, String symbol) {
        this.description = description;
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public String getSymbol() {
        return symbol;
    }

    public static RateUnit fromString(String text) {
        for (RateUnit unit : RateUnit.values()) {
            if (unit.name().equalsIgnoreCase(text) || unit.description.equalsIgnoreCase(text)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid measurement unit: " + text);
    }
}
