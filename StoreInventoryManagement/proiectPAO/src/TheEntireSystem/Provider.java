package TheEntireSystem;

import java.math.BigDecimal;
import java.util.Map;

public class Provider {
    private final String name;
    protected Map<Product, OfferAndStock> offers;

    public Provider(String name, Map<Product, OfferAndStock> offers) {
        this.name = name; this.offers = offers;
    }
    
    public String toString() {
        return  name +
                "\n offers:\n" + offers +
                " \n\n";
    }

    protected void decreaseProductStock(Product p, int toDecrease){
        OfferAndStock tuple = this.offers.get(p);
        tuple.setStock(tuple.getStock() - toDecrease);
        this.offers.put(p, tuple);
    }

    protected OfferAndStock getOfferByProduct(Product p){
        return this.offers.get(p);
    }

    protected BigDecimal getPrice(Product p){
        if(offers.get(p) == null)
            return new BigDecimal(0);
        return offers.get(p).getPrice();
    }

    public String getName() {
        return name;
    }


}
