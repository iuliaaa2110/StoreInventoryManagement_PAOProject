package FranchiseSystem;

import java.math.BigDecimal;

public class Product {

    private final String productName;
    private BigDecimal providerPrice;
    private BigDecimal storePrice;
    private Integer stockManagementId;

    public Product(String productName, BigDecimal providerPrice) {
        this.productName = productName;
        this.providerPrice = providerPrice;
    }

    public Product(String productName, BigDecimal providerPrice, BigDecimal storePrice) {
        this.productName = productName;
        this.providerPrice = providerPrice;
        this.storePrice = storePrice;
    }

    public Product(Product p) {
        this.productName = p.productName;
        this.providerPrice = p.providerPrice;
        this.storePrice = p.storePrice;
    }

    public String toString() {
        return productName;
        // return productName + " (entryPrice = " + providerPrice + "; outPrice = " + storePrice +")";
    }

    BigDecimal calculateAddition(BigDecimal initialPrice){
        return initialPrice.multiply(new BigDecimal(7)).divide(new BigDecimal(100));   // addition price = 7%
    }

    protected String getProductName() {
        return productName;
    }

    BigDecimal getStorePrice() {
        return storePrice;
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

    protected void calculateOutprice(){
        this.storePrice = providerPrice.add(calculateAddition(providerPrice));
    }

}
