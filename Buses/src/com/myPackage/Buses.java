package com.myPackage;

import java.util.Arrays;
import java.util.Objects;
import java.util.*;
import java.util.stream.*;

public class Buses {
    private final List<Bus> busses;
    private int nextNumber;
    static final int DOESNOTEXIST = -1;
    static final int THEREISNOSUCHBUS = -1;

    public Buses() {
        nextNumber = 1;
        busses = new ArrayList<Bus>();
    }
    public int addBus(String name, String[] stops) throws Exception {
        int numberToPass = nextNumber;

        Bus busCandidate = new  Bus(name, stops, numberToPass);

        int busCandidateNumber = doesThisBusExist(busCandidate);
        if (busCandidateNumber == DOESNOTEXIST) {
            busses.add(busCandidate);
            nextNumber += 1;
        }
        else
            throw new RuntimeException("Already exists for " + busCandidateNumber);

        return numberToPass;
    }

    public List<String> sbusesForStop(String targetStop) throws Exception {
        List<String> namesOfBussesContainingTargetStop = new ArrayList<String>();
        for (Bus bus : busses) {
            if (bus.doesThisStopExists(targetStop))
                namesOfBussesContainingTargetStop.add(bus.getName());
        }

        if (namesOfBussesContainingTargetStop.size() == 0)
            throw new RuntimeException("No stop");
        else
            return namesOfBussesContainingTargetStop;
    }

    public Set<Integer> nbusesForStop(String targetStop) throws Exception {
        Set<Integer> numbersOfBussesContainingTargetStop = new HashSet<Integer>();
        for (Bus bus : busses) {
            if (bus.doesThisStopExists(targetStop)) {
                numbersOfBussesContainingTargetStop.add(bus.getNumber());
            }
        }
        if (numbersOfBussesContainingTargetStop.size() == 0)
            throw new RuntimeException("No stop");
        else
            return numbersOfBussesContainingTargetStop;
    }


    public Map<String, Set<Integer>> stopsForBus(String targetBusName) throws Exception {
        Map<String, Set<Integer>> stopsToBussesToSwitchOver =
                new HashMap<String, Set<Integer>>();

        Bus target_bus;
        int numberOfBusWithTargetName = isThereBusWithThisName(targetBusName);
        if (numberOfBusWithTargetName == THEREISNOSUCHBUS)
            throw new RuntimeException("No bus");
        else {
            target_bus = busses.get(numberOfBusWithTargetName);
        }

        for (String stop: target_bus.getStops()) {
            stopsToBussesToSwitchOver.put(stop, nbusesForStop(stop));
        }
        return stopsToBussesToSwitchOver;
    }

    public List<String> allBuses() throws Exception {
        List<String> bussesNames = new ArrayList<String>();
        for (Bus bus : busses) {
            bussesNames.add(bus.getName());
        }
        if (bussesNames.size() == 0)
            throw new RuntimeException("No busses added");
        return allBussesNamesSorted(bussesNames);
    }

    public static List<String> allBussesNamesSorted(List<String> bussesToSort) {
        return bussesToSort.stream().sorted().collect(Collectors.toList());
    }

    private int isThereBusWithThisName(String targetName) {
        int n= busses.size();
        for (int i = 0; i < n; i++) {
            Bus curr = busses.get(i);
            if (Objects.equals(curr.getName(), targetName)) {
                return i;
            }
        }
        return THEREISNOSUCHBUS;
    }

    private int doesThisBusExist(Bus busToCheck) {
        int n = busses.size();

        for (int i = 0; i < n; i++) {
            if (busses.get(i).isTheSameAs(busToCheck)) {
                return i;
            }
        }
        return DOESNOTEXIST;
    }

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
        public boolean doesThisStopExists(String stopToCheck) {
            return Arrays.stream(stops).anyMatch(x -> x == stopToCheck);
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
}
