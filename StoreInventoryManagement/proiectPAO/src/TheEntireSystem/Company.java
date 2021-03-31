package TheEntireSystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// for the moment this class is just an example. It has default values to test the project on
public class Company {
    ArrayList<Store> franchisePoints;
    StoreHouse storeHouse;

    ArrayList<Provider> providers;
    ArrayList<Product> products;


    public Company() {

        Product p0 = new Product("Mozzarella");     // 0
        Product p1 = new Product("Cheese");         // 1
        Product p2 = new Product("Mineral Water");  // 2
        Product p3 = new Product("Patch");          // 3
        Product p4 = new Product("Apple");          // 4
        Product p5 = new Product("Peach");          // 5
        Product p6 = new Product("Banana");         // 6
        Product p7 = new Product("Avocado");        // 7
        Product p8 = new Product("Sprite");         // 8
        Product p9 = new Product("Lay's Chips");    // 9
        Product p10 = new Product("Yogurt");         // 10
        Product p11 = new Product("Salad");          // 11
        Product p12 = new Product("Blue Shirt");     // 12
        Product p13 = new Product("Football ball");  // 13

        products = new ArrayList<Product>() {
            {

                add(p0);
                add(p1);
                add(p2);
                add(p3);
                add(p4);
                add(p5);
                add(p6);
                add(p7);
                add(p8);
                add(p9);
                add(p10);
                add(p11);
                add(p12);
                add(p13);

            }
        };

        Map<Product, OfferAndStock> offers1 = new HashMap<Product, OfferAndStock>() {
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

        Map<Product, OfferAndStock> offers2 = new HashMap<Product, OfferAndStock>() {
            {

                put(products.get(9), new OfferAndStock(new BigDecimal("3.25"), 101));
                put(products.get(3), new OfferAndStock((new BigDecimal("0.3")), 200));
                put(products.get(2), new OfferAndStock(new BigDecimal("1.9"), 13));
                put(products.get(8), new OfferAndStock((new BigDecimal("3.5")), 50));

            }

        };

        Map<Product, OfferAndStock> offers3 = new HashMap<Product, OfferAndStock>() {
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

        providers = new ArrayList<Provider>() {
            {
                add(new Provider("John", offers1)); //0
                add(new Provider("Sam", offers2)); //1
                add(new Provider("Karen", offers3)); //2
            }
        };

        franchisePoints = new ArrayList<Store>() {
            {
                add(new Store("Houston Street 0"));
                add(new Store("Sunset Boulevard 1"));
                add(new Store("N Benedict Canon Drive 2"));

                add(new Supermarket("Rodeo Drive 3"));
                add(new Supermarket("Wall Street 4"));
            }
        };

        storeHouse = StoreHouse.getInstance();

        storeHouse.provide(providers.get(2), p5, 10);
        storeHouse.provide(providers.get(0), p0, 6);
        storeHouse.provide(providers.get(0), p1, 10);
        storeHouse.provide(providers.get(0), p2, 7);
        storeHouse.provide(providers.get(1), p8, 3);
        storeHouse.provide(providers.get(1), p9, 11);
        storeHouse.provide(providers.get(2), p11, 8);
        storeHouse.provide(providers.get(2), p12, 9);
        storeHouse.provide(providers.get(2), p7, 6);
        storeHouse.provide(providers.get(1), p3, 2);
        storeHouse.provide(providers.get(2), p6, 3);
        storeHouse.provide(providers.get(2), p10, 5);

        // initialize Store 0
        franchisePoints.get(0).setStoreStock(new StockManagement((new HashMap<Product, Integer>(){
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
        franchisePoints.get(1).setStoreStock(new StockManagement((new HashMap<Product, Integer>(){
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
        franchisePoints.get(2).setStoreStock(new StockManagement((new HashMap<Product, Integer>(){
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
        franchisePoints.get(3).setStoreStock(new StockManagement((new HashMap<Product, Integer>(){
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

        // initialize Store 3 (Supermarket)
        franchisePoints.get(4).setStoreStock(new StockManagement((new HashMap<Product, Integer>(){
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

    }
}
