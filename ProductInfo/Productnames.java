package ProductInfo;

import java.util.*;
public class Productnames {
    static public String[] productnames(){
        ArrayList<String> products=new ArrayList<>();
        products.add("Rice");
        products.add("Moong");
        products.add("Mukhwas");
        products.add("Apple");
        products.add("Banana");
        products.add("Orange");
        products.add("Milk");
        products.add("Govind Ghee");
        String[] itemsArray = products.toArray(new String[0]);
        return itemsArray;
    }
}
