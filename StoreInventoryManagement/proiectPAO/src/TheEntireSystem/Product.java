package TheEntireSystem;

import java.math.BigDecimal;

public class Product {

    private String productName;
    private BigDecimal providerPrice;
    private BigDecimal storePrice;

    protected Product(String name){

        this.productName = name;
    }

    protected String getProductName() {

        return productName;
    }

    protected BigDecimal getStorePrice() {

        return storePrice;
    }

    // overloading the string, respectively print operator
    public String toString() {

        return productName;
    }

    protected BigDecimal calculateAddition(BigDecimal initialPrice){
        return initialPrice.multiply(new BigDecimal(7)).divide(new BigDecimal(100));   // addition price = 7%
    }

    protected void setProviderPrice(BigDecimal price){
        this.providerPrice = price;

        //Automatically setting the store price
        this.storePrice = this.providerPrice.add(calculateAddition(this.providerPrice));

    };

    protected BigDecimal getProviderPrice() {
        return providerPrice;
    }

}
