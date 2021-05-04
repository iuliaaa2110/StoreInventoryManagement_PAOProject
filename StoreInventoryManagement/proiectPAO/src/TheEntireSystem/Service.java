package TheEntireSystem;

import TheEntireSystem.Franchise;

import java.util.Scanner;
import java.math.BigDecimal;
import java.util.*;

public class Service {

    Franchise franchise = new Franchise();


    //   Show actual stock from the storeHouse
    public void Service1 (){
        franchise.getStoreHouseStock();
    }

    //  Show actual stock of a store
    public void Service2(){
        System.out.println("Write the number of the store for which you wanna see the stock.\n");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        franchise.getStoreStock(nr);
    }

    // Show the bank's gain at the storeHouse
    public void Service3(){

        franchise.getStoreHouseGain();

    }

    // Show the bank's gain of a store
    public void Service4(){
        System.out.println("Write the number of the store for which you wanna see the bank's money.");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        franchise.getStoreGain(nr);
    }

    // Send the money from a store's bank to the StoreHouse's bank
    public void Service5(){
        System.out.println("\nWrite the number of the store who needs to send the money to the StoreHouse.");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        Store store = franchise.getStoreById(nr);

        franchise.collectMoney(store);

    }

    // Refill the stock at a store for specific franchise.products (with the regular quantity or with a specific quantity)
    public void Service6(){

        System.out.println("\nWrite the number of the store who needs the stock refill, the number of the product you need to add, and optional: the quantity. ");
        System.out.println("With spaces between!");
        System.out.println("You can do that all over again. Just press '.' if you re done.\n");

        Scanner keyboard = new Scanner(System.in);
        String st = keyboard.nextLine();

        while(!st.equals(".")){

            String[] t = st.split(" ");

            if(t.length == 2) {
                int storeId = Integer.parseInt(t[0]);
                int productId = Integer.parseInt(t[1]);

                System.out.println(storeId + productId);

                Store store = franchise.getStoreById(storeId);
                Product product = franchise.getProductById(productId);

                franchise.refillProductStock(store, product);
            }
            else if(t.length == 3){
                int nr = Integer.parseInt(t[0]);
                int nr2 = Integer.parseInt(t[1]);
                int quantity = Integer.parseInt(t[2]);

                Store store = franchise.getStoreById(nr);
                Product product = franchise.getProductById(nr2);

                franchise.refillProductStock(store, product, quantity);
            }

            st = keyboard.nextLine();
        }
    }


    // cand nu mai avem in depozit un produs si vrem sa comandam de la un furnizor,
    // iteram prin ofertele furnizorilor si il alegem pe cel care vinde produsul mai ieftin
    // find the provider with the best price for a product
    public void Service7() {
        System.out.println("Write the number of the product you would want to order.");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        Product product = franchise.getProductById(nr);

        franchise.chooseProvider(product);
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

        int quantity = keyboard.nextInt();

        Provider provider = franchise.getProviderById(nr);
        Product product = franchise.getProductById(nr1);

        franchise.provide(provider, product, quantity);

    }

    // make a sell
    // if the client buys a significantly amount of items from a Supermarket he gets a discount
    public void Service9(){


        System.out.println("Type the number of the store where the sell occurs");

        Scanner keyboard = new Scanner(System.in);
        String st = keyboard.nextLine().trim();

        // stiu ca pare o rezolvare foarte odd,
        // dar din nush ce motiv cu nextInt imi dadea eroare java.lang.NumberFormatException: For input string: ""
        String[] t =  st.split(" ");
        int nr = Integer.parseInt(t[0]);

        if(nr > franchise.getPointsNumber() || st.length() > 1)
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

                myMap.put(franchise.getProductById(a), b);

                st = keyboard.nextLine().trim();
            }

            franchise.getStoreById(nr).variedSell(myMap);
        }

    }

    // Calculate the outprice for a product
    public void Service10(){
        System.out.println("Type the number of the product");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        Product product = franchise.getProductById(nr);

        franchise.setOutprice(product);
    }

    public void showOptionsList(){
        System.out.println("Service1  = Show actual stock from the storeHouse \n" +
                "Service2  = Show actual stock of a store \n" +
                "Service3  = Show actual stock of the Storehouse \n" +
                "Service4  = Show the bank's gain of a store \n" +
                "Service5  = Send the money from a store's bank to the StoreHouse's bank \n" +
                "Service6  = Refill the stock at a store for specific franchise.products" +
                "(with the regular quantity or with a specific quantity) \n" +
                "Service7  = Find the provider with the best price for a product \n" +
                "Service8  = Make an order to a provider \n" +
                "Service9  = Make a sell \n" +
                "Service10 = Calculate the outprice for a product \n");
    }

    public void getService() {

        boolean stop = false;

        System.out.println(franchise);
        showOptionsList();

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