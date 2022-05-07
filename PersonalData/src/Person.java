import java.util.*;

public class Person
{
    private HashMap<Integer, String> namesChangeHistory = new HashMap<Integer, String>();
    private HashMap<Integer, String> surnamesChangeHistory = new HashMap<Integer, String>();

    public static void main(String[] args) {
        Person Ivan = new Person();
        Ivan.changeFirstName(2012, "Ivan");
        Ivan.changeLastName(2013, "Debirov");
        System.out.println(Ivan.getFullName(2013));
    }
    void changeFirstName(int year, String first_name)
    {
        if (!namesChangeHistory.containsKey(year)) {
            namesChangeHistory.put(year, first_name);
        }
    }

    void changeLastName(int year, String last_name)
    {
        if (!surnamesChangeHistory.containsKey(year)) {
            surnamesChangeHistory.put(year, last_name);
        }
    }

    String getFullName(int year)
    {
        String firstName = getLatestEntry(year, namesChangeHistory);
        String secondName = getLatestEntry(year, surnamesChangeHistory);

        if (firstName == null && secondName == null) {
            return "Incognito";
        }
        else if (firstName != null && secondName == null) {
            return firstName + " with unknown last name";
        }
        else if (firstName == null && secondName != null) {
            return  secondName + " with unknown first name";
        }
        else {
            return firstName + " " + secondName;
        }
    }

    String getFullNameWithHistory(int year)
    {
        String firstName = getLatestEntry(year, namesChangeHistory);
        String secondName = getLatestEntry(year, surnamesChangeHistory);
        String firstNameHistory = getAllFormattedEntriesBefore(year, namesChangeHistory);
        String secondNameHistory = getAllFormattedEntriesBefore(year, surnamesChangeHistory);

        return firstName + " " + firstNameHistory + " " + secondName + " " + secondNameHistory;
    }


    private String getAllFormattedEntriesBefore(int year, HashMap<Integer, String> dataYearsToStrings) {
        ArrayList<Integer> yearsWhenChangesOccured = new ArrayList<>(dataYearsToStrings.keySet());
        Collections.sort(yearsWhenChangesOccured);

        ArrayList<String> entries = new ArrayList<>();
        int n = yearsWhenChangesOccured.size();
        for (int i = 0; i < n; i++) {
            if (i <= year) {
                entries.add(dataYearsToStrings.get(i));
            } else {
                break;
            }
        }

        if (entries.size() != 0) {
           return  "(" + String.join(", ", entries) + ")";

        }
        else {
            return "";
        }
    }
    private String getLatestEntry(int year, HashMap<Integer, String> dataYearsToStrings)
    {
        ArrayList<Integer> yearsWhenChangesOccured = new ArrayList<>(dataYearsToStrings.keySet());
        Collections.sort(yearsWhenChangesOccured);

        for (Integer i : yearsWhenChangesOccured) {
            if (i <= year) {
                return dataYearsToStrings.get(i);
            }
        }
        return null;
    }
}
