package ProductInfo;

import java.util.*;

public class ProductGSTandPrice {
    public static double pruductHsnAndGSTDetails(Integer hsncode) {
        HashMap<Integer, Double> productsGSTWithHSN = new HashMap<>();
        productsGSTWithHSN.put(23323,18.0);
        productsGSTWithHSN.put(425282,21.7);
        productsGSTWithHSN.put(90232,8.0);
        productsGSTWithHSN.put(95351,17.7);
        productsGSTWithHSN.put(93527,11.0);
        productsGSTWithHSN.put(43825,18.0);
        productsGSTWithHSN.put(328552,24.8);
        productsGSTWithHSN.put(89237,23.3);
        if (productsGSTWithHSN.containsKey(hsncode)) {
            return productsGSTWithHSN.get(hsncode);
        } else {
            return 0;
        }
    }

    public static double pruductHsnAndPriceDetails(Integer hsncode) {
        HashMap<Integer, Double> productsPriceWithHSN = new HashMap<>();
        productsPriceWithHSN.put(23323,120.45);
        productsPriceWithHSN.put(425282,81.77);
        productsPriceWithHSN.put(90232,90.01);
        productsPriceWithHSN.put(95351,56.72);
        productsPriceWithHSN.put(93527,17.32);
        productsPriceWithHSN.put(43825,38.89);
        productsPriceWithHSN.put(328552,24.84);
        productsPriceWithHSN.put(89237,63.87);
        if (productsPriceWithHSN.containsKey(hsncode)) {
            return productsPriceWithHSN.get(hsncode);
        } else {
            return 0;
        }
    }    
}
