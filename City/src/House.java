import java.util.*;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class House {
    private List<Flat> flatList = new ArrayList<>();

    public void addFlat(Flat newFlat) {
        flatList.add(newFlat);
    }

    public HashMap<Integer, Integer> getFlatsInfo() {
        ArrayList<Integer> roomsVariants = new ArrayList<>();
        flatList.forEach( (flat) -> { roomsVariants.add(flat.getNumRooms()); });

        Set<Integer>  uniqueRoomsVariantsSet = new HashSet<Integer>(roomsVariants);
        ArrayList<Integer> uniqueRoomsVariants = new ArrayList<>(uniqueRoomsVariantsSet);

        HashMap<Integer, Integer> roomVariantsCount = new HashMap<>();
        uniqueRoomsVariants.forEach( (numRooms) -> { roomVariantsCount.put(numRooms, Collections.frequency(roomsVariants, numRooms)); } );
        return roomVariantsCount;
    }

    public List<Flat> getGreatestInAreaFlats(int flats) {
        flatList.sort(new FlatAreaComparator());
        Collections.reverse(flatList);
        return flats < flatList.size() ? flatList.subList(0, flats) : flatList;
    }
}

class Flat {
    private int numRooms;
    private float area;

    public Flat(int numRooms, float area) {
        this.numRooms = numRooms;
        this.area = area;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public float getArea() {
        return area;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String toString() {
        return ("Number of rooms :" + numRooms + ", Total area: " + area);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Flat other = (Flat) o;
        return ((other.getArea() == this.area) && (other.getNumRooms() == this.numRooms));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) area;
        hash = 31 * hash + (int) numRooms;
        return hash;
    }
}


class FlatAreaComparator implements Comparator<Flat> {
    @Override
    public int compare(Flat o1, Flat o2) {
        if (o1.equals(o2)) {
            return 0;
        }
        else {
            return o1.getArea() < o2.getArea() ? -1 : 1;
        }
    }
}

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

class FlatTest {

    @Test
    @DisplayName("Get number of rooms")
    void getNumRooms() {
        float area = 25;
        int numberOfRooms = 3;
        Flat myFlat = new Flat(numberOfRooms, area);
        assertEquals(numberOfRooms, myFlat.getNumRooms());
    }

    @Test
    @DisplayName("Get Area")
    void getArea() {
        float area = 25;
        int numberOfRooms = 3;
        Flat myFlat = new Flat(numberOfRooms, area);
        assertEquals(area, myFlat.getArea());
    }

    @Test
    @DisplayName("Set number of rooms")
    void setNumRooms() {
        int numberOfRooms = 3;
        float area = 25;
        Flat myFlat = new Flat(numberOfRooms, area);

        int newNumberOfRooms = 4;
        myFlat.setNumRooms(newNumberOfRooms);
        assertEquals(newNumberOfRooms, myFlat.getNumRooms());
    }

    @Test
    @DisplayName("Set Area")
    void setArea() {
        int numberOfRooms = 3;
        float area = 25;
        Flat myFlat = new Flat(numberOfRooms, area);

        float newArea = 30;
        myFlat.setArea(newArea);
        assertEquals(newArea, myFlat.getArea());
    }

    @Test
    @DisplayName("To String")
    void testToString() {
        int numberOfRooms = 3;
        float area = 25;
        Flat myFlat = new Flat(numberOfRooms, area);
        assertEquals("Number of rooms :3, Total area: 25.0", myFlat.toString());
    }

    @Test
    @DisplayName("Test Equals")
    void testEquals() {
        int numberOfRooms1 = 3;
        int numberOfRooms2 = 3;
        float area1 = 25;
        float area2 = 25;
        Flat Flat1 = new Flat(numberOfRooms1, area1);
        Flat Flat2 = new Flat(numberOfRooms2, area2);
        assertEquals(Flat1, Flat2);

        Flat1.setArea(26);
        assertNotEquals(Flat1, Flat2);
    }

    @Test
    @DisplayName("HashCode")
    void testHashCode() {
        int numberOfRooms1 = 3;
        int numberOfRooms2 = 3;
        float area1 = 25;
        float area2 = 25;
        Flat Flat1 = new Flat(numberOfRooms1, area1);
        Flat Flat2 = new Flat(numberOfRooms2, area2);
        assertEquals(Flat1.hashCode(), Flat2.hashCode());

        Flat1.setArea(26);
        assertNotEquals(Flat1.hashCode(), Flat2.hashCode());
    }
}

class FlatAreaComparatorTest {

    @Test
    @DisplayName("Compare Test")
    void test() {
        FlatAreaComparator myComparator = new FlatAreaComparator();
        int numberOfRooms1 = 3;
        int numberOfRooms2 = 3;
        float area1 = 25;
        float area2 = 25;
        Flat Flat1 = new Flat(numberOfRooms1, area1);
        Flat Flat2 = new Flat(numberOfRooms2, area2);
        assertEquals(myComparator.compare(Flat1, Flat2), 0);

        Flat1.setArea(24);
        assertEquals(myComparator.compare(Flat1, Flat2), -1);

        Flat2.setArea(20);
        assertEquals(myComparator.compare(Flat1, Flat2), 1);
    }
}

