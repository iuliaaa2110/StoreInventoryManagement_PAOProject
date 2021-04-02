package TheEntireSystem;

import java.math.BigDecimal;
import java.util.*;

// for the moment this class is just an example. It has default values to test the project on
public class Company {
    private ArrayList<Store> franchisePoints;
    private StoreHouse storeHouse;

    private ArrayList<Provider> providers;
    private ArrayList<Product> products;


    protected Company() {

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

        // initialize Store 4 (Supermarket)
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


    //   Show actual stock from the storeHouse
    protected void Service1 (){
        System.out.println("Actual stock of the storeHouse is " + storeHouse.mainStock.getStock() + " . \n");
    }

    //  Show actual stock of a store
    protected void Service2(){
        System.out.println("Write the number of the store for which you wanna see the stock.\n");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        System.out.println("You chose " + franchisePoints.get(nr).getAddress() + " store");
        System.out.println("Actual stock of the store" +  franchisePoints.get(nr).getAddress() +"is " + franchisePoints.get(nr).storeStock.getStock() + " .\n");
    }

    // Show the bank's gain at the storeHouse
    protected void Service3(){

        System.out.println("StoreHouse's bank = " + storeHouse.getMainBank());

    }

    // Show the bank's gain of a store
    protected void Service4(){
        System.out.println("Write the number of the store for which you wanna see the bank's money.");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        System.out.println("The amount of money at the store right now is: " + franchisePoints.get(nr).getStoreBank() + " . \n");
    }

    // Send the money from a store's bank to the StoreHouse's bank
    protected void Service5(){

        System.out.println("\nWrite the number of the store who needs to send the money to the StoreHouse.");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        System.out.println("StoreHouse's bank before: " + storeHouse.getMainBank());

        franchisePoints.get(nr).clearBank();

        System.out.println("StoreHouse's bank now: " + storeHouse.getMainBank());

    }

    // Refill the stock at a store for specific products (with the regular quantity or with a specific quantity)
    protected void Service6(){

        System.out.println("\nWrite the number of the store who needs the stock refill, the number of the product you need to add, and optional: the quantity. ");
        System.out.println("With spaces between!");
        System.out.println("You can do that all over again. Just press '.' if you re done.\n");

        Scanner keyboard = new Scanner(System.in);
        String st = keyboard.nextLine();

        while(!st.equals(".")){

            String[] t = st.split(" ");

            if(t.length == 2) {
                int nr = Integer.parseInt(t[0]);
                int nr2 = Integer.parseInt(t[1]);

                System.out.println(nr + nr2);

                franchisePoints.get(nr).refillProductStock(products.get(nr2));
            }
            else if(t.length == 3){
                int nr = Integer.parseInt(t[0]);
                int nr2 = Integer.parseInt(t[1]);
                int nr3 = Integer.parseInt(t[2]);

                franchisePoints.get(nr).refillProductStock(products.get(nr2), nr3);
            }

            st = keyboard.nextLine();
        }
    }


    // cand nu mai avem in depozit un produs si vrem sa comandam de la un furnizor,
    // iteram prin ofertele furnizorilor si il alegem pe cel care vinde produsul mai ieftin
    // find the provider with the best price for a product
    protected void Service7() {
        System.out.println("Write the number of the product you would want to order.");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        Product p = products.get(nr);

        PriorityQueue<Map.Entry<BigDecimal, Provider>> pq = new PriorityQueue<>(Map.Entry.comparingByKey());

        for (Provider provider : providers)
            if (!provider.getPrice(p).equals(new BigDecimal(0)))
                pq.offer(new AbstractMap.SimpleEntry<>(provider.getPrice(p), provider));

        if (pq.isEmpty())
            System.out.println("There are no such things in the whole country!! everyone is sold out on this product");
        else {
            assert pq.peek() != null;

            Provider chooseProvider = pq.peek().getValue();

            System.out.println(chooseProvider.getName());
        }
    }


    // make an order to a provider
    protected void Service8(){
        System.out.println();
        System.out.println("Write the number of the provider you wanna buy from");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        System.out.println("Write the number of the product you wanna order");

        int nr1 = keyboard.nextInt();

        System.out.println("Write the quantity you need");

        int nr2 = keyboard.nextInt();

        storeHouse.provide(providers.get(nr), products.get(nr1), nr2);

    }

    // make a sell
    // if the client buys a significantly amount of items from a Supermarket he gets a discount
    protected void Service9(){

        System.out.println("Type the number of the store where the sell occurs");

        Scanner keyboard = new Scanner(System.in);
        String st = keyboard.nextLine().trim();

        // stiu ca pare o rezolvare foarte odd,
        // dar din nush ce motiv cu nextInt imi dadea eroare java.lang.NumberFormatException: For input string: ""
        String[] t =  st.split(" ");
        int nr = Integer.parseInt(t[0]);

        if(nr > franchisePoints.size() || st.length() > 1)
            System.out.println("There is no store with this number/Wrong input.");
        else {

            Map<Product, Integer> myMap = new HashMap<>();

            System.out.println("And then type productNumber and quantity with space between." +
                    " Do it how many times you need ( one pair at a line ). Type '.' if you re done.");

            st = keyboard.nextLine().trim();
            //        st = keyboard.nextLine();
            //        int Q = Integer.parseInt(tempStr.trim());

            while (!st.equals(".")) {
                t = st.split(" ");
                int a = Integer.parseInt(t[0]);
                Integer b = Integer.parseInt(t[1]);

                myMap.put(products.get(a), b);

                st = keyboard.nextLine().trim();
            }

            franchisePoints.get(nr).variedSell(myMap);
        }

    }

    //see the entry price and the out price of a product
    protected void Service10(){

        System.out.println("Type the number of the product");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        if(products.get(nr).getProviderPrice() == null)
            System.out.println("First you need to make an order for this product. Do it at Service 8 ");
        else
            System.out.println("entryPrice = " + products.get(nr).getProviderPrice() + "\noutPrice = "
                    + products.get(nr).getStorePrice());
    }

}
