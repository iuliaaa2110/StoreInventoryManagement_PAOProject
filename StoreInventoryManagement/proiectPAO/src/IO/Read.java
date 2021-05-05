package IO;

import TheEntireSystem.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Read {
    private static Read instance = null;

    private Read() {
    }

    public static Read getInstance() {
        if (instance == null)
            instance = new Read();
        return instance;
    }

    public static ArrayList<Product> readProducts(){
        try(var in = new BufferedReader(new FileReader("StoreInventoryManagement/proiectPAO/data/products.csv"))){

            String line;

            ArrayList<Product> products = new ArrayList<>();

            while ( (line = in.readLine()) != null) {
                String []fields = line.replaceAll(" ", "").split(",");

                if(fields.length > 1)
                    products.add(new Product(fields[0], new BigDecimal(fields[1]), new BigDecimal(fields[2])));
                else
                    products.add(new Product(fields[0]));
            }

            return products;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Provider> readProviders(){
        try(var in = new BufferedReader(new FileReader("StoreInventoryManagement/proiectPAO/data/providers.csv"))){

            String line;

            ArrayList<Provider> providers = new ArrayList<>();

            while ( (line = in.readLine()) != null) {
                String []fields = line.replaceAll(" ", "").split(",");

                String providerName = fields[0];
                String[] elements = fields[1].replaceAll(" ", "").split("/");

                Map<Product, OfferAndStock> offers = new HashMap<>();

                for(String element:elements)
                {
                    String []s = element.replaceAll(" ", "").split("=");
                    Product product = new Product(s[0]);
                    String []price_stock = s[1].replaceAll(" ", "").split(";");
                    OfferAndStock offer = new OfferAndStock(new BigDecimal(price_stock[0]), Integer.parseInt(price_stock[1]));

                    offers.put(product, offer);
                }

                providers.add(new Provider(providerName, offers));
            }

            return providers;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Store> readStores(){
        try(var in = new BufferedReader(new FileReader("StoreInventoryManagement/proiectPAO/data/stores.csv"))){
            String line;

            ArrayList<Store> stores = new ArrayList<>();

            while ( (line = in.readLine()) != null) {
                String []fields = line.replaceAll(" ", "").split(",");

                if(fields[1].equals("ST"))
                    stores.add(new Store(fields[0]));
                else
                    stores.add(new Supermarket(fields[0]));
            }

            return stores;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}