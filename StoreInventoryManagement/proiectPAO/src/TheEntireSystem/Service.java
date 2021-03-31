package TheEntireSystem;

import java.util.Scanner;
import java.math.BigDecimal;
import java.util.*;

public class Service {

    Company company = new Company();


    //   Show actual stock from the storeHouse
    public void Service1 (){
        System.out.println("Actual stock of the storeHouse is " + company.storeHouse.mainStock.getStock() + " . \n");
    }

    //  Show actual stock of a store
    public void Service2(){
        System.out.println("Write the number of the store for which you wanna see the stock.\n");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        System.out.println("You chose " + company.franchisePoints.get(nr).getAddress() + " store");
        System.out.println("Actual stock of the store is " + company.franchisePoints.get(nr).storeStock.getStock() + " .\n");
    }

    // Show the bank's gain at the company.storeHouse
    public void Service3(){

        System.out.println("StoreHouse's bank = " + company.storeHouse.getMainBank());

    }

    // Show the bank's gain of a store
    public void Service4(){
        System.out.println("Write the number of the store for which you wanna see the bank's money.");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        System.out.println("The amount of money at the store right now is: " + company.franchisePoints.get(nr).getStoreBank() + " . \n");
    }

    // Send the money from a store's bank to the StoreHouse's bank
    public void Service5(){

        System.out.println("\nWrite the number of the store who needs to send the money to the StoreHouse.");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        System.out.println("StoreHouse's bank before: " + company.storeHouse.getMainBank());

        company.franchisePoints.get(nr).clearBank();

        System.out.println("StoreHouse's bank now: " + company.storeHouse.getMainBank());

    }

    // Refill the stock at a store for specific company.products (with the regular quantity or with a specific quantity)
    public void Service6(){

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

                company.franchisePoints.get(nr).refillProductStock(company.products.get(nr2));
            }
            else if(t.length == 3){
                int nr = Integer.parseInt(t[0]);
                int nr2 = Integer.parseInt(t[1]);
                int nr3 = Integer.parseInt(t[2]);

                company.franchisePoints.get(nr).refillProductStock(company.products.get(nr2), nr3);
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

        Product p = company.products.get(nr);

        PriorityQueue<Map.Entry<BigDecimal, Provider>> pq = new PriorityQueue<>(Map.Entry.comparingByKey());

        for (Provider provider : company.providers)
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
    public void Service8(){
        System.out.println();
        System.out.println("Write the number of the provider you wanna buy from");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        System.out.println("Write the number of the product you wanna order");

        int nr1 = keyboard.nextInt();

        System.out.println("Write the quantity you need");

        int nr2 = keyboard.nextInt();

        company.storeHouse.provide(company.providers.get(nr), company.products.get(nr1), nr2);

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

        if(nr > company.franchisePoints.size() || st.length() > 1)
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

                myMap.put(company.products.get(a), b);

                st = keyboard.nextLine().trim();
            }

            company.franchisePoints.get(nr).variedSell(myMap);
        }

    }

    //see the entry price and the out price of a product
    public void Service10(){

        System.out.println("Type the number of the product");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        if(company.products.get(nr).getProviderPrice() == null)
            System.out.println("First you need to make an order for this product. Do it at Service 8 ");
        else
            System.out.println("entryPrice = " + company.products.get(nr).getProviderPrice() + "\noutPrice = "
                    + company.products.get(nr).getStorePrice());
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