import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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