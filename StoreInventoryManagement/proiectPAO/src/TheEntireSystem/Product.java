package TheEntireSystem;

import java.math.BigDecimal;

public class Product {

    private final String productName;
    private BigDecimal providerPrice;
    private BigDecimal storePrice;

    Product(String name){

        this.productName = name;
    }

    String getProductName() {

        return productName;
    }

    BigDecimal getStorePrice() {

        return storePrice;
    }

    // overloading the string, respectively print operator
    public String toString() {

        return productName;
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
