package FranchiseSystem;

import java.math.BigDecimal;
import java.util.*;

public class Store {

    protected String address;
    protected StockManagement storeStock = new StockManagement();
    protected int regularStockSize = 7; // regular amount of stock per product
    protected int maxTotalStockSize = 100;
    protected BigDecimal storeBank = new BigDecimal(0);
    protected String stockManagementCSV;

    public Store(String address) {
        this.address = address;

    }

//    public Store(String address) {
//        this.address = address;
//
//    }

    public Store(String address, BigDecimal storeBank, StockManagement storeStock, String stockManagemenCSV){
        this.address = address;
        this.storeBank = storeBank;
        this.storeStock = storeStock;
        this.stockManagementCSV = stockManagemenCSV;
    }

    public String toString() {
        return address + '\n' +
                ", storeStock=" + storeStock +
                ", regularStockSize=" + getRegularStockSize() +
                ", maxTotalStockSize=" + getMaxTotalStockSize() +
                ", storeBank=" + storeBank +
                "}\n";
    }

    public String Columns(){
        return address + ",ST," + storeBank + "," + stockManagementCSV;
    }

    public String stockColumns(){
        return storeStock.Columns();
    }

    protected void setStoreStock(StockManagement initialStock) {
        this.storeStock = initialStock;
    }

    protected void Sell(String productName, Integer requiredQuantity){
        Product p = getProductByName(productName);

        if(p == null || this.storeStock.getProductStock(p) == 0) {

            System.out.println("Sorry. We are sold out for this product: " + productName
                    +" . We will bring more of it soon!");
        }
        else {
            if(p.getStorePrice() == null)
                System.out.println("We need to refill the stock for this product first:" + p + " Try with Service 6.");
            else {

                Integer actualQuantity = this.storeStock.getProductStock(p);

                if (requiredQuantity <= actualQuantity) {

                    this.storeBank = this.storeBank.add(p.getStorePrice().multiply(new BigDecimal(requiredQuantity)));
                    this.storeStock.updateStock(p, -requiredQuantity);

                    System.out.println(productName + ": Successfully sold. ");
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

    protected void variedSell( HashMap<String, Integer> products){
        for (Map.Entry<String, Integer> e : products.entrySet()) {
            String key    = e.getKey();
            Integer value  = e.getValue();

            Sell(key, value);
        }
    }

    protected Integer getActualStockSize(){
        return storeStock.actualStockSize();
    }

    public String getAddress() {
        return address;
    }

    protected int getRegularStockSize() {
        return regularStockSize;
    }

    public BigDecimal getStoreBank() {
        return storeBank;
    }

    protected int getMaxTotalStockSize() {
        return maxTotalStockSize;
    }

    public String getStockManagementCSV() {
        return stockManagementCSV;
    }

    protected Product getProductByName(String name){
        return storeStock.getProductByName(name);
    }
}
