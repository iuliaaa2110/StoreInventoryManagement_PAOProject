package FranchiseSystem;

import ExternalComponents.Provider;

import java.util.Scanner;
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

        System.out.println("\nWrite the number of the store who needs the stock refill, the name of the " +
                "product you need to add, and optional: the final quantity you want to have.\n");
        System.out.println("With spaces between!");
        System.out.println("You can do that all over again. Just press '.' when you re done.");
        System.out.println("Example: 2 MineralWater 3");

        Scanner keyboard = new Scanner(System.in);
        String st = keyboard.nextLine();

        while(!st.equals(".")){

            String[] t = st.trim().split(" ");

            int storeId = Integer.parseInt(t[0]);
            Store store = franchise.getStoreById(storeId);

            if (t.length == 2) {
                franchise.refillProductStock(store, t[1]);
            } else if (t.length == 3) {
                int quantity = Integer.parseInt(t[2]);
                franchise.refillProductStock(store, t[1], quantity);
            }

            st = keyboard.nextLine();
        }
    }

    public void find_provider() {
        // cand nu mai avem in depozit un produs si vrem sa comandam de la un furnizor,
        // iteram prin ofertele furnizorilor si il alegem pe cel care vinde produsul mai ieftin
        // find the provider with the best price for a product
        System.out.println("Write the name of the product you would want to order.");

        Scanner keyboard = new Scanner(System.in);
        String product = keyboard.nextLine();

        franchise.chooseProvider(product);
    }

    public void order(){
        Scanner keyboard = new Scanner(System.in);

        String product;
        Provider provider;

        System.out.println();
        System.out.println("Write the number (or the name) of the provider you wanna buy from");

        if(keyboard.hasNextInt())
            provider = franchise.getProviderById(keyboard.nextInt());
        else
            provider = franchise.getProviderById(keyboard.nextLine());

        System.out.println("Write the name of the product you wanna order");
        product = keyboard.nextLine();

        System.out.println("Write the quantity you need");
        int quantity = keyboard.nextInt();

        franchise.provide(provider, product, quantity);

    }

    // if the client buys a significantly amount of items from a Supermarket he gets a discount
    public void sell(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Type the number of the store where the sell occurs");

        Store store = franchise.getStoreById(keyboard.nextInt());

        if(store == null)
            System.out.println("There is no store with this number/Wrong input.");
        else {

            HashMap<String, Integer> myMap = new HashMap<>();

            System.out.println("Type the order: product and quantity with space between, one pair at a line . Type '.' if you re done. Exp: Cheese 10");
            // fara acest blank imi da eroare, chiar si cu
            //        if(keyboard.hasNext())
            String blank = keyboard.nextLine();
            String s = keyboard.nextLine();
            String[] fields = s.split(" ");

            while (!fields[0].equals(".")) {

                if(fields.length != 2){
                    System.out.println("Wrong format");
                    break;
                }

                myMap.put(fields[0], Integer.parseInt(fields[1]));
                fields = keyboard.nextLine().trim().split(" ");
            }

            store.variedSell(myMap);
        }

    }

    public void show_outprice(){
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Type the number of the store");
        int i = keyboard.nextInt();
        Store store = franchise.getStoreById(i);

        if(store == null)
            System.out.println("There is no store with this number.");
        else{
            System.out.println("Type the name of the product");
            String name = "";

            if(keyboard.hasNext())
                name = keyboard.nextLine();

            Product product = store.getProductByName(name);

            if(product != null)
                System.out.println(product.getStorePrice());
            else
                System.out.println(store.getAddress() + " has not this product in stock");
        }
    }

    public void show_capacity_status(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Type the number of the store you wanna find out the capacity status for");

        Store store = franchise.getStoreById(keyboard.nextInt());

        System.out.println("Max capacity: " + store.getMaxTotalStockSize());
        System.out.println("Actual capacity: " + store.getActualStockSize());
        System.out.println("Default stock refill per one type of product: " + store.regularStockSize);

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
                "6.  refill                = Refill the stock at a store for specific productsincreasing " +
                "the stock to the desired quantity." +
                "(either to the regular quantity, either to a specific quantity) \n" +
                "7.  find_provider         = Find the provider with the best price for a product \n" +
                "8.  order                 = Make an order to a provider \n" +
                "9.  sell                  = Make a sell \n" +
                "10. show_outprice    = Calculate the outprice for a product at a given store \n" +
                "11. show_capacity_status  = Show capacity status for a given store\n");
    }

    public void updateCSV(){
        franchise.UpdateCSV();
    }
}