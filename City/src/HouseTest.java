import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest {
    @Test
    @DisplayName("Get Flats information")
    void getFlatsInfo() {
        House myHouse = new House();
        myHouse.addFlat(new Flat(1, 20));
        myHouse.addFlat(new Flat(1, 20));
        myHouse.addFlat(new Flat(3, 30));



        Map<Integer, Integer> result = new HashMap<>();
        result.put(1, 2);
        result.put(3, 1);

        assertEquals(result, myHouse.getFlatsInfo());
    }

    @Test
    @DisplayName("Get Greatest in Area Flats")
    void getGreatestInAreaFlats() {
        House myHouse = new House();
        myHouse.addFlat(new Flat(1, 10));
        myHouse.addFlat(new Flat(1, 20));
        myHouse.addFlat(new Flat(1, 30));

        List<Flat> result = new ArrayList<>();
        result.add(new Flat(1, 30));
        result.add(new Flat(1, 20));

        assertEquals(result, myHouse.getGreatestInAreaFlats(2));

        result.add(new Flat(1, 10));


        assertEquals(result, myHouse.getGreatestInAreaFlats(3));
    }
}