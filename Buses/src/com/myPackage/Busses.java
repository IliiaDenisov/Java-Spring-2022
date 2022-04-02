package com.myPackage;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;

public class Busses {
    public Busses() {
        next_number = 1;
        busses = new ArrayList<Bus>();
    }
    public int addBus(String name, String[] stops) throws Exception {
        int number_to_pass = next_number;

        Bus bus_candidate = new  Bus(name, stops, number_to_pass);
        if (!doesThisBusExist(bus_candidate)) {
            busses.add(bus_candidate);
            next_number += 1;
        }
        else
            throw new RuntimeException("The given Bus already exists");

        return number_to_pass;
    }

    List<String> sbusesForStop(String nameStop) throws Exception {
        assert true;
    }

    Set<Integer> nbusesForStop(String nameStop) throws Exception {

    }

    Map<String, Set<Integer>> stopsForBus(String name) {

    }

    List<String> allBuses() throws Exception {

    }


    private boolean doesThisBusExist(Bus bus_to_check) {
        int number_of_busses = busses.size();
        boolean bus_to_check_exists = false;
        for (Bus bus : busses) {
            if (bus.isTheSameAs(bus_to_check)) {
                return true;
            }
        }
        return false;
    }
    private List<Bus> busses;
    private int next_number;
}
