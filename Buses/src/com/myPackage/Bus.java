package com.myPackage;

import java.util.Arrays;
import java.util.Objects;

public class Bus {
    public Bus(String name, String[] stops, int number) {
        this.stops = stops;
        this.name = name;
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
    public boolean isTheSameAs(Bus other) {
        return ((Objects.equals(this.name, other.name))
                && (Arrays.equals(this.stops, other.stops)));
    }
    public String[] getStops() {
        return stops;
    }
    public String getName() {
        return name;
    }
    private final String[] stops;
    private final String name;
    private final int number;

}
