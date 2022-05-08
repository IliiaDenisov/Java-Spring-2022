import java.util.*;

public class Person
{
    private HashMap<Integer, String> namesChangeHistory = new HashMap<Integer, String>();
    private HashMap<Integer, String> surnamesChangeHistory = new HashMap<Integer, String>();

    public static void main(String[] args) {
        Person Ivan = new Person();
        Ivan.changeFirstName(2010, "Iran");
        Ivan.changeFirstName(2011, "Iban");
        Ivan.changeFirstName(2012, "Ivan");
        Ivan.changeLastName(2013, "Debirov");
        Ivan.changeLastName(2014, "Denisov");
        System.out.println(Ivan.getFullNameWithHistory(2010));
        System.out.println(Ivan.getFullNameWithHistory(2011));
        System.out.println(Ivan.getFullNameWithHistory(2012));
        System.out.println(Ivan.getFullNameWithHistory(2013));
        System.out.println(Ivan.getFullNameWithHistory(2014));
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

        return firstName + " " + firstNameHistory + " " + secondName + " " + secondNameHistory + " ";
    }


    private String getAllFormattedEntriesBefore(int year, HashMap<Integer, String> dataYearsToStrings) {
        ArrayList<Integer> yearsWhenChangesOccured = new ArrayList<>(dataYearsToStrings.keySet());
        Collections.sort(yearsWhenChangesOccured);

        ArrayList<String> entries = new ArrayList<>();
        int n = yearsWhenChangesOccured.size();
        for (int i = 0; i < n; i++) {
            int currYear = yearsWhenChangesOccured.get(i);
            if (currYear <= year) {
                entries.add(dataYearsToStrings.get(currYear));
            } else {
                break;
            }
        }

        if (entries.size() > 1) {
            entries.remove(entries.size() - 1);
            Set<String> uniqueEntries = new HashSet<String>(entries);
            return "(" + String.join(", ", uniqueEntries) + ")";
        }
        else {
            return "";
        }
    }
    private String getLatestEntry(int year, HashMap<Integer, String> dataYearsToStrings)
    {
        ArrayList<Integer> yearsWhenChangesOccured = new ArrayList<>(dataYearsToStrings.keySet());
        Collections.sort(yearsWhenChangesOccured);

        int newIndex = findIndex(yearsWhenChangesOccured, year);
        if (newIndex > 0) {
            int latestYear = yearsWhenChangesOccured.get(newIndex - 1);
            return dataYearsToStrings.get(latestYear);
        }
        else {
            return "";
        }
    }

    private int findIndex(ArrayList<Integer> arr, int K)
    {
        int n = arr.size();
        for(int i = 0; i < n; i++)
            if (arr.get(i) == K)
                return i + 1;
            else if (arr.get(i) >= K)
                return i;
        return n;
    }
}

