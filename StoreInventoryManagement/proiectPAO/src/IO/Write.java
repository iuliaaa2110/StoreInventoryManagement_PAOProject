package IO;

import FranchiseSystem.Product;
import ExternalComponents.Provider;
import FranchiseSystem.Store;
import FranchiseSystem.StoreHouse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;


public class Write {
    private static Write instance = null;

    private Write() {
    }

    public static Write getInstance() {
        if (instance == null)
            instance = new Write();
        return instance;
    }

    public static void writeAudit(String operation) {
        try (var out = new BufferedWriter(new FileWriter("StoreInventoryManagement/proiectPAO/data/audit.csv", true))) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            out.write(operation + ", " + timestamp.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeProducts(ArrayList<Product> products){
        try{
            var writer = new FileWriter("StoreInventoryManagement/proiectPAO/data/products.csv");
            for(var product : products){
                writer.write(product.Columns());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }

    public void writeProviders(ArrayList<Provider> providers){
        try{
            var writer = new FileWriter("StoreInventoryManagement/proiectPAO/data/providers.csv");
            for(var provider : providers){
                writer.write(provider.Columns());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }

    public void writeStorehouse(StoreHouse storehouse){
        try{
            var writer = new FileWriter("StoreInventoryManagement/proiectPAO/data/storehouse.csv");
            writer.write(storehouse.Columns());
            writer.write("\n");

            writeStockManagement(storehouse.getStockManagementCSV(), storehouse.stockColumns());

            writer.close();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
    }

    public void writePoints(ArrayList<Store> points){
        try{
            var writer = new FileWriter("StoreInventoryManagement/proiectPAO/data/stores.csv");
            for(var point : points){
                writer.write(point.Columns());
                writer.write("\n");

                writeStockManagement(point.getStockManagementCSV(), point.stockColumns());
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }

    public void writeStockManagement(String file, String output){
        try{
            var writer = new FileWriter("StoreInventoryManagement/proiectPAO/data/StockManagementCSVs/" + file);
            writer.write(output);
            writer.close();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
    }
    // WriteStores??
}
