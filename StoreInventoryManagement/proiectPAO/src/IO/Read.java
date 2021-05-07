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

    public ArrayList<Provider> readProviders(){
        try(var in = new BufferedReader(new FileReader("StoreInventoryManagement/proiectPAO/data/providers.csv"))){

            String line;

            ArrayList<Provider> providers = new ArrayList<>();

            while ( (line = in.readLine()) != null) {
                String []fields = line.replaceAll(" ", "").split(",");

                String providerName = fields[0];
                String[] elements = fields[1].replaceAll(" ", "").split("/");

                HashMap<String, OfferAndStock> offers = new HashMap<>();

                for(String element:elements)
                {
                    String []s = element.replaceAll(" ", "").split("=");

                    String []price_stock = s[1].replaceAll(" ", "").split(";");
                    OfferAndStock offer = new OfferAndStock(new BigDecimal(price_stock[0]), Integer.parseInt(price_stock[1]));

                    offers.put(s[0], offer);
                }

                providers.add(new Provider(providerName, offers));
            }

            return providers;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Store> readStores(){
        try(var in = new BufferedReader(new FileReader("StoreInventoryManagement/proiectPAO/data/stores.csv"))){
            String line;

            ArrayList<Store> stores = new ArrayList<>();

            while ( (line = in.readLine()) != null) {
                String []fields = line.replaceAll(" ", "").split(",");
                String path = "StoreInventoryManagement/proiectPAO/data/StockManagementCSVs/" + fields[3];
                StockManagement stockManagement = readStockManagement(path);

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


    public static StockManagement readStockManagement(String path){
        try(var in = new BufferedReader(new FileReader(path))){

            String line;

            Map<Product, Integer> stock= new HashMap<>();

            while ( (line = in.readLine()) != null) {
                String []fields = line.replaceAll(" ", "").split(",");

                if(fields.length != 3)
                {
                    System.out.println("Datele de intrare nu sunt in formatul corect!/ Fisierul nu contine nici o coloana");
                    break;
                }

                Product product = new Product(fields[0], new BigDecimal(fields[1]));
                stock.put(product, Integer.parseInt(fields[2]));

            }

            return new StockManagement(stock);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    //StoreHouse o sa aiba mereu o singura linie!
    public StoreHouse readStoreHouse(){
        try(var in = new BufferedReader(new FileReader("StoreInventoryManagement/proiectPAO/data/storehouse.csv"))){

            String line;

            if ((line = in.readLine()) != null){
                String []fields = line.replaceAll(" ", "").split(",");

                String stockPath ="StoreInventoryManagement/proiectPAO/data/StockManagementCSVs/" + fields[1];

                return StoreHouse.getInstance(readStockManagement(stockPath), new BigDecimal(fields[0]),  fields[1]);
            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
