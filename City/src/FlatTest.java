import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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