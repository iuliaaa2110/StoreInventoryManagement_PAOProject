package FranchiseSystem;

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

    @Override
    public boolean equals(Object o){
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Product)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Product p = (Product) o;

        return this.productName.equals(p.productName);
    }

    public String toString() {
        return productName;
        // return productName + " (entryPrice = " + providerPrice + "; outPrice = " + storePrice +")";
    }

    public String Columns() {
        if(providerPrice ==  null)
            return productName + ",,";

        if(storePrice ==  null)
            return productName + "," + providerPrice +",";

        return productName + "," + providerPrice + "," + storePrice;
    }

    public String getProductName() {
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

}
