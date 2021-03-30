package TheEntireSystem;

import java.math.BigDecimal;
import java.util.*;

public class Store {

    public String address;
    protected StoreHouse storeHouse = StoreHouse.getInstance();
    protected StockManagement storeStock = new StockManagement();
    protected static int regularStock = 7; // regular amount of stock per product
    protected static int maxTotalStock = 100;
    protected BigDecimal storeBank = new BigDecimal(0);
    private boolean x = false;


    public Store(String address){
        this.address = address;

    }

    // fiecare store poate fi initializat o singura data!
    protected void setStoreStock(StockManagement initializeStock) {

        if(!x)
            this.storeStock = initializeStock;
        x = true;
    }

    protected void Sell(Product p, Integer soldQuantity){
        if(this.storeStock.getProductStock(p) == 0) {

            System.out.println("Sorry. We are sold out for this product: " + p.getProductName() +" . We will bring more of it soon!");
        }
        else {
            if(p.getStorePrice() == null)
                System.out.println("You need to refill the stock for this product first:" + p + " Try with Service 6.");
            this.storeBank = this.storeBank.add(p.getStorePrice());
            this.storeStock.updateStock(p, -soldQuantity);

            System.out.println("Successfully sold. ");
        }
    }

    protected void variedSell( Map<Product, Integer> products){
        for (Map.Entry<Product, Integer> e : products.entrySet()) {
            Product key    = e.getKey();
            Integer value  = e.getValue();

            Sell(key, value);
        }
    }

    protected void refillProductStock(Product p){
        this.refillProductStock(p, regularStock);
    }

    public void refillProductStock(Product p, int desiredQuantity){

        //doar daca in stock ul magazinului mai e loc

        if(!this.storeStock.isFull(maxTotalStock)) {

            Integer currentQuantity;
            Integer availableQuantity;

            if(this.storeStock.getProductStock(p) == 0)
                currentQuantity = 0;
            else
                currentQuantity = this.storeStock.getProductStock(p);

            Integer neededQuantity = desiredQuantity - currentQuantity;

            if(storeHouse.mainStock.getProductStock(p) == 0)
                availableQuantity= 0;
            else
                availableQuantity = storeHouse.mainStock.getProductStock(p);

            if (neededQuantity == 0) {

                System.out.println("Stock already full.");
            } else {
                if (availableQuantity >= neededQuantity) {

                    this.storeStock.updateStock(p, desiredQuantity);
                    storeHouse.mainStock.updateStock(p, availableQuantity - neededQuantity);

                    System.out.println("The stock of " + p.getProductName() + " succesfully refilled at " + this.address + " store");

                } else {

                    this.storeStock.updateStock(p, currentQuantity + availableQuantity);
                    storeHouse.mainStock.updateStock(p, 0);

                    System.out.println("Could only be added " + availableQuantity + " pieces. The main stock (from the TheEntireSystem.StoreHouse) of " + p.getProductName() + " is empty now.");
                }
            }
        }
        else
            System.out.println("No more free space in the store for bringing other items :( .");
    }

    // trimite banii incasati la depozitul central (storehouse)
    protected void clearBank(){
        storeHouse.collects(this.storeBank);

//        this.storeBank -= this.storeBank;
        this.storeBank = new BigDecimal(0);
    }

    public String getAddress() {
        return address;
    }

//    protected setStoreStock(TheEntireSystem.StockManagement storeStock) {
//        this.storeStock = storeStock;
//    }

//    public static int getRegularStock() {
//        return regularStock;
//    }

    protected BigDecimal getStoreBank() {
        return storeBank;
    }

//    void setStoreBank(BigDecimal storeBank) {
//        this.storeBank = storeBank;
//    }
}
