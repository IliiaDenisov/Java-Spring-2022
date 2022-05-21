import java.util.*;
import java.util.Comparator;

public class House {
    private List<Flat> flatList = new ArrayList<>();
    public static void main(String[] args) {
        /*
        House myHouse = new House();
        myHouse.addFlat(new Flat(1, 20));
        myHouse.addFlat(new Flat(1, 23));
        myHouse.addFlat(new Flat(2, 35));
        myHouse.addFlat(new Flat(2, 20));
        myHouse.addFlat(new Flat(4, 50));
        System.out.println(myHouse.getFlatsInfo());
        */
    }

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

