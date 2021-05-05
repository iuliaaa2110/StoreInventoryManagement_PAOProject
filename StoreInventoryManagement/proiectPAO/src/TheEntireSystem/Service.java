package TheEntireSystem;

import TheEntireSystem.Franchise;

import java.util.Scanner;
import java.math.BigDecimal;
import java.util.*;

public class Service {

    Franchise franchise = Franchise.getInstance();

    public void show_storehouse_stock (){
        franchise.getStoreHouseStock();
    }

    public void show_store_stock(){
        System.out.println("Write the number of the store for which you wanna see the stock.\n");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        franchise.getStoreStock(nr);
    }

    public void show_storehouse_bank(){
        franchise.getStoreHouseGain();

    }

    public void show_store_bank(){
        System.out.println("Write the number of the store for which you wanna see the bank's money.");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        franchise.getStoreGain(nr);
    }

    public void collect_gain(){
        System.out.println("\nWrite the number of the store who needs to send the money to the StoreHouse.");

        Scanner keyboard = new Scanner(System.in);
        int nr = keyboard.nextInt();

        Store store = franchise.getStoreById(nr);

        franchise.collectMoney(store);

    }

    public void refill(){

        System.out.println("\nWrite the number of the store who needs the stock refill, \nthe name of the " +
                "product you need to add, \n and optional: the final quantity you want to have.\n");
        System.out.println("With spaces between!");
        System.out.println("You can do that all over again. Just press '.' when you re done.\n");
        System.out.println("Example: 2 Watter 25");

        Scanner keyboard = new Scanner(System.in);
        String st = keyboard.nextLine();

        while(!st.equals(".")){

            String[] t = st.split(" ");

            if(t.length == 2) {
                int storeId = Integer.parseInt(t[0]);
                Store store = franchise.getStoreById(storeId);
                Product product = franchise.getProductById(t[1]);

                franchise.refillProductStock(store, product);
            }
            else if(t.length == 3){
                Store store = franchise.getStoreById(Integer.parseInt(t[0]));
                Product product = franchise.getProductById(t[1]);
                int quantity = Integer.parseInt(t[2]);

                franchise.refillProductStock(store, product, quantity);
            }

            st = keyboard.nextLine();
        }
    }

    // cand nu mai avem in depozit un produs si vrem sa comandam de la un furnizor,
    // iteram prin ofertele furnizorilor si il alegem pe cel care vinde produsul mai ieftin
    // find the provider with the best price for a product
    public void find_provider() {
        System.out.println("Write the number or the name of the product you would want to order.");

        Scanner keyboard = new Scanner(System.in);

        Product product;

        if(keyboard.hasNextInt())
            product = franchise.getProductById(keyboard.nextInt());
        else
            product = franchise.getProductById(keyboard.nextLine());

        if(product != null)
            franchise.chooseProvider(product);
        else
            System.out.println("This product does not exist");
    }

    public void order(){
        Scanner keyboard = new Scanner(System.in);

        Product product;
        Provider provider;

        System.out.println();
        System.out.println("Write the number (or the name) of the provider you wanna buy from");

        if(keyboard.hasNextInt())
            provider = franchise.getProviderById(keyboard.nextInt());
        else
            provider = franchise.getProviderById(keyboard.nextLine());

        System.out.println("Write the number (or the name) of the product you wanna order");

        if(keyboard.hasNextInt())
            product = franchise.getProductById(keyboard.nextInt());
        else
            product = franchise.getProductById(keyboard.nextLine());

        System.out.println("Write the quantity you need");
        int quantity = keyboard.nextInt();

        franchise.provide(provider, product, quantity);

    }

    // if the client buys a significantly amount of items from a Supermarket he gets a discount
    public void sell(){
        System.out.println("Type the store where the sell occurs");

        Scanner keyboard = new Scanner(System.in);
        String st = keyboard.nextLine().trim();

        String[] t =  st.split(" ");
        int nr = Integer.parseInt(t[0]);
        Product product;

        if(nr > franchise.getPointsNumber() || st.length() > 1)
            System.out.println("There is no store with this number/Wrong input.");
        else {

            Map<Product, Integer> myMap = new HashMap<>();

            System.out.println("And then type product and quantity with space between." +
                    " Do it how many times you need ( one pair at a line ). Type '.' if you re done.");

            st = keyboard.nextLine().trim();

            while (!st.equals(".")) {
                t = st.split(" ");
                myMap.put(franchise.getProductById(t[0]), Integer.parseInt(t[1]));
                st = keyboard.nextLine().trim();
            }

            franchise.getStoreById(nr).variedSell(myMap);
        }

    }

    public void calculate_outprice(){
        System.out.println("Type the number (or the name) of the product");

        Scanner keyboard = new Scanner(System.in);
        Product product;

        if(keyboard.hasNextInt())
            product = franchise.getProductById(keyboard.nextInt());
        else
            product = franchise.getProductById(keyboard.nextLine());

        if(product != null)
            franchise.setOutprice(product);
        else
            System.out.println("Wrong input/ The product does not exist");
    }

    public void showOptionsList(){
        System.out.println(franchise);

        System.out.println("Services:\n");
        System.out.println(
                "1.  show_storehouse_stock = Show actual stock from the storeHouse \n" +
                "2.  show_store_stock      = Show actual stock of a store \n" +
                "3.  show_storehouse_bank  = Show the main bank (the bank from the Storehouse) \n" +
                "4.  show_store_bank       = Show the bank's gain of a store \n" +
                "5.  collect_gain          = Send the money from a store's bank to the StoreHouse's bank \n" +
                "6.  refill                = Refill the stock at a store for specific products, inccreasing " +
                "the stock to the desired quantity." +
                "(either to the regular quantity, either to a specific quantity) \n" +
                "7.  find_provider         = Find the provider with the best price for a product \n" +
                "8.  order                 = Make an order to a provider \n" +
                "9.  sell                  = Make a sell \n" +
                "10. calculate_outprice    = Calculate the outprice for a product \n");

    }

    public void updateCSV(){
        franchise.UpdateCSV();
    }
}