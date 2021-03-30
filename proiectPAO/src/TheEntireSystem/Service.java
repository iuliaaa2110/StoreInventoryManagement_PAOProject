package TheEntireSystem;

import java.util.Scanner;
import java.math.BigDecimal;
import java.util.*;


public class Service {

    ArrayList<Store> franchisePoints;
    StoreHouse storeHouse;

    ArrayList<Provider> providers;
    ArrayList<Product> products;


    public Service() {

        products = new ArrayList<Product>() {
            {

                add(new Product("Mozzarella"));     // 0
                add(new Product("Cheese"));         // 1
                add(new Product("Mineral Water"));  // 2
                add(new Product("Patch"));          // 3
                add(new Product("Apple"));          // 4
                add(new Product("Peach"));          // 5
                add(new Product("Banana"));         // 6
                add(new Product("Avocado"));        // 7
                add(new Product("Sprite"));         // 8
                add(new Product("Lay's Chips"));    // 9
                add(new Product("Yogurt"));         // 10
                add(new Product("Salad"));          // 11
                add(new Product("Blue Shirt"));     // 12
                add(new Product("Football ball"));  // 13

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


        storeHouse = StoreHouse.getInstance();

        // initialize storeHouse
        storeHouse.setMainStock(new StockManagement((new HashMap<Product, Integer>(){
            {
                put(products.get(0),5);
                put(products.get(1),5);
                put(products.get(2),5);
                put(products.get(3),5);
                put(products.get(4),10);
                put(products.get(5),10);
                put(products.get(6),10);
                put(products.get(7),10);
                put(products.get(8),10);
            }
        })));

    }


    // cand nu mai avem in depozit un produs si vrem sa comandam de la un furnizor,
    // iteram prin ofertele furnizorilor si il alegem pe cel care vinde produsul mai ieftin
    public Provider chooseProvider(Product p){

        PriorityQueue<Map.Entry<BigDecimal, Provider>> pq = new PriorityQueue<>(Map.Entry.comparingByKey());

        for(Provider provider : providers)
            if(!provider.getPrice(p).equals(new BigDecimal(0)))
                pq.offer(new AbstractMap.SimpleEntry<>(provider.getPrice(p), provider));

        assert pq.peek() != null;
        return pq.peek().getValue();
    }

    //   Show actual stock from the StoreHouse
    public void Service1 (){
        System.out.println("Actual stock of the StoreHouse is " + storeHouse.mainStock.getStock() + " . \n");
    }

    //  Show actual stock of a store
    public void Service2(){
        System.out.println("Write the number of the store for which you wanna see the stock.\n");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        System.out.println("You chose " + franchisePoints.get(nr).getAddress() + " store");
        System.out.println("Actual stock of the store is " + franchisePoints.get(nr).storeStock.getStock() + " .\n");
    }

    // Show the bank's gain at the StoreHouse
    public void Service3(){

        System.out.println("StoreHouse's bank = " + storeHouse.getMainBank());

    }

    // Show the bank's gain of a store
    public void Service4(){
        System.out.println("Write the number of the store for which you wanna see the bank's money.");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        System.out.println("The amount of money at the store right now is: " + franchisePoints.get(nr).getStoreBank() + " . \n");
    }

    // Send the money from a store's bank to the StoreHouse's bank
    public void Service5(){

        System.out.println("\nWrite the number of the store who needs to send the money to the TheEntireSystem.StoreHouse.");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        System.out.println("TheEntireSystem.StoreHouse's bank before: " + storeHouse.getMainBank());

        franchisePoints.get(nr).clearBank();

        System.out.println("TheEntireSystem.StoreHouse's bank now: " + storeHouse.getMainBank());

    }

    // Refill the stock at a stores for specific products (with the regular quantity or with a specific quantity)
    public void Service6(){

        System.out.println("\nWrite the number of the store who needs the stock refill, the number of the product you need to add, and optional: the quantity. ");
        System.out.println("With spaces between!");
        System.out.println("You can do that all over again. Just press '.' if you re done.\n");

        Scanner keyboard = new Scanner(System.in);
        String st = keyboard.nextLine();

        while(!st.equals(".")){

            String[] t = st.split(" ");

            if(t.length == 2) {
                System.out.println("e ok");
                int nr = Integer.parseInt(t[0]);
                System.out.println("am ajuns aici");
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


    // find the provider with the best price for a product
    public void Service7(){
        System.out.println("Write the number of the product you would want to order.");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        System.out.println( chooseProvider(products.get(nr)).getName());
    }

    // make an order to a provider
    public void Service8(){
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
    // if you buy a significantly amount of items from a Supermarket you get a discount
    public void Service9(){

        System.out.println("Type the number of the store where the sell occurs");
        System.out.println("And then type productNumber and quantity with space between." +
                " Do it how many times you need ( one pair at a line ). Type '.' if you re done.");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        Map<Product, Integer> myMap = new HashMap<>();

        String st = keyboard.nextLine();

        while(!st.equals("."))
        {
            String[] t = st.split(" ");
            int a = Integer.parseInt(t[0]);
            Integer b = Integer.parseInt(t[1]);
            myMap.put(products.get(a), b);

            st = keyboard.nextLine();
        }

        franchisePoints.get(nr).variedSell(myMap);

    }

    //see the entry price and the out price of a product
    public void Service10(){

        System.out.println("Type the number of the product");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        if(products.get(nr).getProviderPrice() == null)
            System.out.println("The product was not purchased by any store yet");
        else
            System.out.println("entryPrice = " + products.get(nr).getProviderPrice() + "\noutPrice = "
                    + products.get(nr).getStorePrice());
    }

    public void getService() {

        boolean stop = false;

        while (!stop){
            System.out.println("\n~Enter the service you want to use: ");
            Scanner keyboard = new Scanner(System.in);
            String serviceNumber = keyboard.nextLine();
            try{
                switch (serviceNumber) {
                    case "Service1" : Service1();break;
                    case "Service2" : Service2();break;
                    case "Service3" : Service3();break;
                    case "Service4" : Service4();break;
                    case "Service5" : Service5();break;
                    case "Service6" : Service6();break;
                    case "Service7" : Service7();break;
                    case "Service8" : Service8();break;
                    case "Service9" : Service9();break;
                    case "Service10" : Service10();break;

                    case "stop" : stop = true;

                }
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }

}