package IO;

import ExternalComponents.OfferAndStock;
import FranchiseSystem.Product;
import ExternalComponents.Provider;
import FranchiseSystem.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import FranchiseSystem.StockManagement;

public class Read {
    private static Read instance = null;

    private Read() {
    }

    public static Read getInstance() {
        if (instance == null)
            instance = new Read();
        return instance;
    }

    public ArrayList<Product> readProducts(){
        try(var in = new BufferedReader(new FileReader("StoreInventoryManagement/proiectPAO/data/products.csv"))){

            String line;

            ArrayList<Product> products = new ArrayList<>();

            while ( (line = in.readLine()) != null) {
                String []fields = line.replaceAll(" ", "").split(",");

                if(fields.length == 3)
                    products.add(new Product(fields[0], new BigDecimal(fields[1]), new BigDecimal(fields[2])));
                if(fields.length == 1)
                    products.add(new Product(fields[0]));
                if(fields.length == 2)
                {
                    System.out.println(fields[1]);
                products.add(new Product(fields[0], new BigDecimal(fields[1])));}
            }

            return products;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Provider> readProviders(ArrayList<Product> products){
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
                    int i;

                    if(products.contains(product)) {
                        i = products.indexOf(product);
                        product = products.get(i);
                    }
                    else{
                        products.add(product);
                    }

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

    public ArrayList<Store> readStores(ArrayList<Product> products){
        try(var in = new BufferedReader(new FileReader("StoreInventoryManagement/proiectPAO/data/stores.csv"))){
            String line;

            ArrayList<Store> stores = new ArrayList<>();

            while ( (line = in.readLine()) != null) {
                String []fields = line.replaceAll(" ", "").split(",");
                String path = "StoreInventoryManagement/proiectPAO/data/StockManagementCSVs/" + fields[3];
                StockManagement stockManagement = readStockManagement(path , products);

                if(fields[1].equals("ST"))
                    stores.add(new Store(fields[0], new BigDecimal(fields[2]), stockManagement, fields[3]));
                else
                    stores.add(new Supermarket(fields[0], new BigDecimal(fields[2]), stockManagement, fields[3]));
            }

            return stores;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public StockManagement readStockManagement(String path, ArrayList<Product> products){
        try(var in = new BufferedReader(new FileReader(path))){

            String line;

            Map<Product, Integer> stock= new HashMap<>();

            while ( (line = in.readLine()) != null) {
                String []fields = line.replaceAll(" ", "").split(",");

                if(fields.length != 1)
                    System.out.println("Datele de intrare nu sunt in formatul corect!");

                Product product = new Product(fields[0]);
                int i;

                if(products.contains(product)) {
                    i = products.indexOf(product);
                    stock.put(products.get(i), Integer.parseInt(fields[1]));
                }
                else{
                    System.out.println("Unknown product!");
                }
            }

            return new StockManagement(stock);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //StoreHouse o sa aiba mereu o singura linie!
    public StoreHouse readStoreHouse(ArrayList<Product> products){
        try(var in = new BufferedReader(new FileReader("StoreInventoryManagement/proiectPAO/data/storehouse.csv"))){

            String line;

            if ((line = in.readLine()) != null){
                String []fields = line.replaceAll(" ", "").split(",");

                String stockPath ="StoreInventoryManagement/proiectPAO/data/StockManagementCSVs/" + fields[1];

                return StoreHouse.getInstance(readStockManagement(stockPath, products), new BigDecimal(fields[0]),  fields[1]);
            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
