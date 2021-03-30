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

    protected void setStorePrice(){

        this.storePrice = this.providerPrice.add(calculateAddition(this.providerPrice));

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
        this.setStorePrice();
    };

    public BigDecimal getProviderPrice() {
        return providerPrice;
    }

    // commented
//      @Override
//        public int hashCode() {
//            int result=17;
//            result=31*result+age;
//            result=31*result+(productName!=null ? productName.hashCode():0);
//            return result;
//        }

}
