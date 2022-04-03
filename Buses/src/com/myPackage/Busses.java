package com.myPackage;

import java.util.*;
import java.util.stream.*;

public class Busses {
    public Busses() {
        next_number = 1;
        busses = new ArrayList<Bus>();
    }
    public int addBus(String name, String[] stops) throws Exception {
        int number_to_pass = next_number;

        Bus bus_candidate = new  Bus(name, stops, number_to_pass);

        int bus_candidate_number = doesThisBusExist(bus_candidate);
        if (bus_candidate_number == DOESNOTEXIST) {
            busses.add(bus_candidate);
            next_number += 1;
        }
        else
            throw new RuntimeException("Already exists for " + bus_candidate_number);

        return number_to_pass;
    }

    public List<String> sbusesForStop(String target_stop) throws Exception {
        List<String> names_of_busses_containing_target_stop = new ArrayList<String>();
        for (Bus bus : busses) {
            if (bus.doesThisStopExists(target_stop))
                names_of_busses_containing_target_stop.add(bus.getName());
        }

        if (names_of_busses_containing_target_stop.size() == 0)
            throw new RuntimeException("No stop");
        else
            return names_of_busses_containing_target_stop;
    }

    public Set<Integer> nbusesForStop(String target_stop) throws Exception {
        Set<Integer> numbers_of_busses_containing_target_stop = new HashSet<Integer>();
        for (Bus bus : busses) {
            if (bus.doesThisStopExists(target_stop)) {
                numbers_of_busses_containing_target_stop.add(bus.getNumber());
            }
        }
        if (numbers_of_busses_containing_target_stop.size() == 0)
            throw new RuntimeException("No stop");
        else
            return numbers_of_busses_containing_target_stop;
    }


    public Map<String, Set<Integer>> stopsForBus(String target_bus_name) throws Exception {
        Map<String, Set<Integer>> stops_to_busses_to_switch_over =
                new HashMap<String, Set<Integer>>();
        Bus target_bus =
        for (String stop: target_bus.getStops()) {
            stops_to_busses_to_switch_over.put(stop, nbusesForStop(stop));
        }
        return  stops_to_busses_to_switch_over;
    }

    public List<String> allBuses() throws Exception {
        List<String> busses_names = new ArrayList<String>();
        for (Bus bus : busses) {
            busses_names.add(bus.getName());
        }
        if (busses_names.size() == 0)
            throw new RuntimeException("No busses added");
        return allBussesNamesSorted(busses_names);
    }

    public static List<String> allBussesNamesSorted(List<String> busses_to_sort) {
        return busses_to_sort.stream().sorted().collect(Collectors.toList());
    }

    private int isThereBusWithThisName(String name) {
        // TODO: add the body
        return THEREISNOSUCHBUS;
    }

    private int doesThisBusExist(Bus bus_to_check) {
        int number_of_busses = busses.size();
        boolean bus_to_check_exists = false;
        for (int i = 0; i < number_of_busses; i++) {
            if (busses.get(i).isTheSameAs(bus_to_check)) {
                return i;
            }
        }
        return DOESNOTEXIST;
    }
    private List<Bus> busses;
    private int next_number;
    static final int DOESNOTEXIST = -1;
    static final int THEREISNOSUCHBUS = -1;
}
