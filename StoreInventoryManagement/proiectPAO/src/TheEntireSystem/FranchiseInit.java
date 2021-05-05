package TheEntireSystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// this class gives some default values to test the project on
public class FranchiseInit {
    ArrayList<Product> initProducts() {

        ArrayList<Product> products = new ArrayList<>();

        String[] productNames = {"Mozzarella", "Cheese" , "Mineral Water",  "Patch", "Apple", "Peach", "Banana" ,
                "Avocado", "Sprite", "Lay's Chips", "Yogurt", "Salad", "Blue Shirt", "Football ball"};

        for (String productName : productNames) {
            products.add(new Product(productName));
        }
        return products;
    }

    ArrayList<Provider> initProviders(ArrayList<Product> products)
    {
        Map<Product, OfferAndStock> offers1 = new HashMap<>() {
            {

                put(products.get(0), new OfferAndStock(new BigDecimal("5.3"), 100));
                put(products.get(2), new OfferAndStock((new BigDecimal("2.2")), 120));
                put(products.get(1), new OfferAndStock(new BigDecimal("3.7"), 35));
                put(products.get(6), new OfferAndStock((new BigDecimal("1.3")), 50));
                put(products.get(7), new OfferAndStock(new BigDecimal("5.4"), 30));
                put(products.get(11), new OfferAndStock((new BigDecimal("0.5")), 20));
                put(products.get(13), new OfferAndStock((new BigDecimal("30")), 300));
            }
        };

        Map<Product, OfferAndStock> offers2 = new HashMap<>() {
            {

                put(products.get(9), new OfferAndStock(new BigDecimal("3.25"), 101));
                put(products.get(3), new OfferAndStock((new BigDecimal("0.3")), 200));
                put(products.get(2), new OfferAndStock(new BigDecimal("1.9"), 13));
                put(products.get(8), new OfferAndStock((new BigDecimal("3.5")), 50));

            }

        };

        Map<Product, OfferAndStock> offers3 = new HashMap<>() {
            {

                put(products.get(10), new OfferAndStock(new BigDecimal("4"), 50));
                put(products.get(2), new OfferAndStock((new BigDecimal("2.2")), 400));
                put(products.get(1), new OfferAndStock(new BigDecimal("3.7"), 35));
                put(products.get(6), new OfferAndStock((new BigDecimal("1.3")), 50));
                put(products.get(7), new OfferAndStock(new BigDecimal("5.4"), 30));
                put(products.get(12), new OfferAndStock((new BigDecimal("60")), 50));
                put(products.get(5), new OfferAndStock(new BigDecimal("2.0"), 30));
                put(products.get(11), new OfferAndStock((new BigDecimal("0.45")), 19));

            }
        };

        return new ArrayList<>() {
            {
                add(new Provider("John", offers1));     //0
                add(new Provider("Sam", offers2));      //1
                add(new Provider("Karen", offers3));    //2
            }
        };
    }

    ArrayList<Store> initFranchisePoints(ArrayList<Product> products)
    {
        ArrayList<Store> franchisePoints = new ArrayList<>() {
            {
                add(new Store("Houston Street 0"));
                add(new Store("Sunset Boulevard 1"));
                add(new Store("N Benedict Canon Drive 2"));

                add(new Supermarket("Rodeo Drive 3"));
                add(new Supermarket("Wall Street 4"));
            }
        };

        franchisePoints.get(0).setStoreStock(new StockManagement((new HashMap<>(){
            {
                put(products.get(0),5);
                put(products.get(1),5);
                put(products.get(2),5);
                put(products.get(3),5);
                put(products.get(4),7);
                put(products.get(8),10);
            }
        })));

        // initialize Store 1
        franchisePoints.get(1).setStoreStock(new StockManagement((new HashMap<>(){
            {
                put(products.get(11),7);
                put(products.get(1),5);
                put(products.get(2),5);
                put(products.get(3),14);
                put(products.get(5),3);
                put(products.get(12),10);
                put(products.get(7),15);
                put(products.get(9),10);
            }
        })));

        // initialize Store 2
        franchisePoints.get(2).setStoreStock(new StockManagement((new HashMap<>(){
            {
                put(products.get(11),1);
                put(products.get(1),2);
                put(products.get(2),9);
                put(products.get(3),18);
                put(products.get(4),2);
                put(products.get(5),3);
                put(products.get(12),10);
                put(products.get(7),15);
                put(products.get(9),10);
                put(products.get(6),12);
            }
        })));

        // initialize Store 3 (Supermarket)
        franchisePoints.get(3).setStoreStock(new StockManagement((new HashMap<>(){
            {
                put(products.get(11),20);
                put(products.get(1),2);
                put(products.get(2),40);
                put(products.get(3),18);
                put(products.get(4),2);
                put(products.get(5),3);
                put(products.get(12),11);
                put(products.get(7),15);
                put(products.get(9),7);
                put(products.get(6),12);
                put(products.get(8),12);
                put(products.get(10),3);
            }
        })));

        // initialize Store 4 (Supermarket)
        franchisePoints.get(4).setStoreStock(new StockManagement((new HashMap<>(){
            {
                put(products.get(11),20);
                put(products.get(1),2);
                put(products.get(2),40);
                put(products.get(3),18);
                put(products.get(4),2);
                put(products.get(5),3);
                put(products.get(12),22);
                put(products.get(7),1);
                put(products.get(9),19);
                put(products.get(6),19);
                put(products.get(8),19);
            }
        })));


        return franchisePoints;
    }

    StoreHouse initStoreHouse(ArrayList<Provider> providers, ArrayList<Product> products){

        StockManagement stock = new StockManagement();

        stock.updateStock(products.get(5), 10);
        stock.updateStock(products.get(0), 6);
        stock.updateStock(products.get(1), 10);
        stock.updateStock(products.get(2), 7);
        stock.updateStock(products.get(8), 3);
        stock.updateStock(products.get(9), 11);
        stock.updateStock(products.get(11), 8);
        stock.updateStock(products.get(12), 9);
        stock.updateStock(products.get(7), 6);
        stock.updateStock(products.get(3), 2);
        stock.updateStock(products.get(6), 3);
        stock.updateStock(products.get(10), 5);

        return StoreHouse.getInstance(stock, new BigDecimal(1500));
    }

}
