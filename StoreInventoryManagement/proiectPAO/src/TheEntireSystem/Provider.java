package TheEntireSystem;

import java.math.BigDecimal;
import java.util.Map;

public class Provider {
    private final String name;
    protected Map<Product, OfferAndStock> offers;

    public Provider(String name) {
        this.name = name;
    }

    public Provider(String name, Map<Product, OfferAndStock> offers) {
        this.name = name; this.offers = offers;
    }
    
    public String toString() {
        return  name +
                "\n offers:\n" + offers +
                " \n\n";
    }

    public String Columns(){

        String s = name + ",";

        for(Map.Entry<Product, OfferAndStock> entry : offers.entrySet()){
            Product p = entry.getKey();
            OfferAndStock o = entry.getValue();
            s += p.getProductName() + "=" + o.getPrice() + ";" + o.getStock() +"/";
        }

        return s;
    }

    @Override
    public boolean equals(Object o){
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Provider)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Provider p = (Provider) o;

        return this.name.equals(p.name);
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
