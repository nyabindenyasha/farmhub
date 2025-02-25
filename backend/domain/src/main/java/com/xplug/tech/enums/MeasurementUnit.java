package com.xplug.tech.enums;

public enum MeasurementUnit {
    // Mass Units
    GRAM("Gram", "g"),
    KILOGRAM("Kilogram", "kg"),
    TON("Ton", "t"),

    // Volume Units
    LITRE("Litre", "L"),
    MILLILITRE("Millilitre", "mL"),

    // Length Units
    METER("Meter", "m"),
    CENTIMETER("Centimeter", "cm"),

    // Temperature Units
    CELSIUS("Celsius", "°C"),
    FAHRENHEIT("Fahrenheit", "°F");

    private final String name;
    private final String symbol;

    MeasurementUnit(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public static MeasurementUnit fromString(String text) {
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            if (unit.name().equalsIgnoreCase(text) || unit.getName().equalsIgnoreCase(text)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid measurement unit: " + text);
    }
}

