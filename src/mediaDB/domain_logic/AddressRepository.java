package mediaDB.domain_logic;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class AddressRepository {
    private Set<Integer> addresses = Collections.synchronizedSet(new LinkedHashSet<>());

    //    TODO: Thread safety?
    public String  nextAddress(){
        if (addresses.isEmpty()) {
            addresses.add(0);
            return "0";
        }
        Integer lastElement = null;
        for (Integer address : addresses) {
            lastElement = address;
        }
        int newElement = lastElement+1;
        int oldSize = addresses.size();
        if (oldSize > newElement)
            throw new RuntimeException("Calculated address smaller than expected.");
        addresses.add(newElement);
        if (addresses.size() <= oldSize)
            throw new RuntimeException("Calculated address already exists.");
        return Integer.toString(newElement);
    }
}
