package TheEntireSystem;

import java.math.BigDecimal;
import java.util.*;

public class Store {

    protected String address;
    protected StockManagement storeStock = new StockManagement();
    protected static int regularStockSize = 7; // regular amount of stock per product
    protected static int maxTotalStockSize = 100;
    protected BigDecimal storeBank = new BigDecimal(0);

    public Store(String address) {
        this.address = address;

    }

    public String toString() {
        return address + '\n' +
                ", storeStock=" + storeStock +
                ", regularStockSize=" + getRegularStockSize() +
                ", maxTotalStockSize=" + getMaxTotalStockSize() +
                ", storeBank=" + storeBank +
                "}\n";
    }

    protected void setStoreStock(StockManagement initialStock) {
        this.storeStock = initialStock;
    }

    protected void Sell(Product p, Integer requiredQuantity){
        if(this.storeStock.getProductStock(p) == 0) {

            System.out.println("Sorry. We are sold out for this product: " + p.getProductName() +" . We will bring more of it soon!");
        }
        else {
            if(p.getStorePrice() == null)
                System.out.println("We need to refill the stock for this product first:" + p + " Try with Service 6.");
            else {

                Integer actualQuantity = this.storeStock.getProductStock(p);

                if (requiredQuantity <= actualQuantity) {

                    this.storeBank = this.storeBank.add(p.getStorePrice().multiply(new BigDecimal(requiredQuantity)));
                    this.storeStock.updateStock(p, -requiredQuantity);

                    System.out.println("Successfully sold. ");
                } else {

                    this.storeBank = this.storeBank.add(p.getStorePrice().multiply(new BigDecimal(actualQuantity)));
                    this.storeStock.updateStock(p, -actualQuantity);

                    System.out.println("Could only sell " + actualQuantity + " pieces of " + p.getProductName() +
                            "                            \". The store stock of\" +\n" +
                            "                            p.getProductName() + \" is empty now.\"");
                }

            }
        }
    }

    protected void variedSell( Map<Product, Integer> products){
        for (Map.Entry<Product, Integer> e : products.entrySet()) {
            Product key    = e.getKey();
            Integer value  = e.getValue();

            Sell(key, value);
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    protected int getRegularStockSize() {
        return regularStockSize;
    }

    protected BigDecimal getStoreBank() {
        return storeBank;
    }

    protected int getMaxTotalStockSize() {
        return maxTotalStockSize;
    }

    public StockManagement getStoreStock() {
        return storeStock;
    }
}
