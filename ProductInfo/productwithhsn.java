package ProductInfo;
import java.util.*;
public class productwithhsn {
    public static int pruductHSNDetails(String pName) {
        HashMap<String, Integer> productsWithHSN = new HashMap<>();
        productsWithHSN.put("Apple", 23323);
        productsWithHSN.put("Banana", 425282);
        productsWithHSN.put("Orange", 90232);
        productsWithHSN.put("Milk", 95351);
        productsWithHSN.put("Moong", 93527);
        productsWithHSN.put("Rice", 43825);
        productsWithHSN.put("Govind Ghee", 328552);
        productsWithHSN.put("Mukhwas", 89237);
        if (productsWithHSN.containsKey(pName)) {
            return productsWithHSN.get(pName);
        } else {
            return 0;
        }
    }
}
