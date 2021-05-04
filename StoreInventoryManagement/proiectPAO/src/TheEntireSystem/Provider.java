package TheEntireSystem;

import java.math.BigDecimal;
import java.util.Map;

public class Provider {

//    protected Map<TheEntireSystem.Product, Pair<Integer, Integer>> offer;
    private final String name;
    protected Map<Product, OfferAndStock> offers;

    protected Provider(String name, Map<Product, OfferAndStock> offers) {
        this.name = name; this.offers = offers;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "name='" + name + '\'' +
                ", offers=" + offers +
                '}';
    }

    protected void decreaseProductStock(Product p, int toDecrease){
        OfferAndStock tuple = this.offers.get(p);
        tuple.setStock(tuple.getStock() - toDecrease);
        this.offers.put(p, tuple);
    }

    protected OfferAndStock getOfferByProduct(Product p){
        return this.offers.get(p);
    }

    protected Map<Product, OfferAndStock> getOffers() {
        return offers;
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
