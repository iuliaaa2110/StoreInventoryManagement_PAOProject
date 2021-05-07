package FranchiseSystem;

import java.math.BigDecimal;

public class Product {

    private final String productName;
    private BigDecimal providerPrice;
    private BigDecimal storePrice;

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

        // daca au acelasi pret de intrare, automat vor avea (daca va fi calculat) si acelasi pret de iesire deci
        // sunt identice.
        // a fost totusi nevoie de override ul asta pt ca s-ar putea ca pentru unul din ele sa fi fost deja
        // calculat pretul de iesire si pt celalalt nu.
        return this.productName.equals(p.productName) && this.providerPrice.equals(p.providerPrice);
    }

    public String toString() {
        return productName;
        // return productName + " (entryPrice = " + providerPrice + "; outPrice = " + storePrice +")";
    }

    // Utils:
    BigDecimal calculateAddition(BigDecimal initialPrice){
        return initialPrice.multiply(new BigDecimal(7)).divide(new BigDecimal(100));   // addition price = 7%
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

    protected void calculateOutprice(){
        this.storePrice = providerPrice.add(calculateAddition(providerPrice));
    }

}
