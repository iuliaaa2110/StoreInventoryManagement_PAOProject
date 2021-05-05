package TheEntireSystem;

import java.math.BigDecimal;

public class Product {

    private final String productName;
    private BigDecimal providerPrice;
    private BigDecimal storePrice;

    public Product(String name){
        this.productName = name;
    }

    public Product (String productName,  BigDecimal providerPrice){
        this.productName = productName;
        this.providerPrice = providerPrice;
    }

    public Product(String productName, BigDecimal providerPrice, BigDecimal storePrice) {
        this.productName = productName;
        this.providerPrice = providerPrice;
        this.storePrice = storePrice;
    }

    String getProductName() {
        return productName;
    }

    BigDecimal getStorePrice() {
        return storePrice;
    }

    public String toString() {
        return productName;
        // return productName + " (entryPrice = " + providerPrice + "; outPrice = " + storePrice +")";
    }

    void setProviderPrice(BigDecimal price){
        this.providerPrice = price;
    }

    protected void setStorePrice(BigDecimal price){
        this.storePrice = price;
    }

    BigDecimal getProviderPrice() {
        return providerPrice;
    }

}
