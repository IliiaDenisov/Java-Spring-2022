import javax.naming.NameClassPair;
import java.util.ArrayList;
import java.util.HashMap;

public class Person
{
    private HashMap<Integer, String> namesChangeHistory = new HashMap<Integer, String>();
    private HashMap<Integer, String> surnamesChangeHistory = new HashMap<Integer, String>();

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
        String resultName;
        if (namesChangeHistory.containsKey(year) && surnamesChangeHistory.containsKey(year)) {
            resultName = namesChangeHistory.get(year) + " " + surnamesChangeHistory.get(year);
        }
        else if (!namesChangeHistory.containsKey(year) && surnamesChangeHistory.containsKey(year)) {
            resultName = surnamesChangeHistory.get(year) + " with unknown first name";
        }
        else if (namesChangeHistory.containsKey(year) && !surnamesChangeHistory.containsKey(year)) {
            resultName = namesChangeHistory.get(year) + " with unknown last name";
        }
        else {
            resultName = "Incognito";
        }
        return resultName;
    }

    String getFullNameWithHistory(int year)
    {

    }

}
