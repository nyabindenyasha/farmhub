package com.xplug.tech.utils;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class RandomUtils {

    private RandomUtils() {
    }

    public static String uuidGenerator() {
        return String.valueOf(System.nanoTime());
    }

    public static String generateToken() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
    }

    public static String generateNameBasedUUID(String string) {
        UUID nameBasedUUID = Generators.nameBasedGenerator().generate(string);
        return nameBasedUUID.toString();
    }
}
