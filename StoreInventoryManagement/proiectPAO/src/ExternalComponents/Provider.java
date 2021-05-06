package ExternalComponents;

import FranchiseSystem.Product;

import java.math.BigDecimal;
import java.util.Map;

public class Provider {
    private final String name;
    private Map<Product, OfferAndStock> offers;

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

        StringBuilder s = new StringBuilder(name + ",");

        for(Map.Entry<Product, OfferAndStock> entry : offers.entrySet()){
            Product p = entry.getKey();
            OfferAndStock o = entry.getValue();
            s.append(p.getProductName()).append("=").append(o.getPrice()).append(";").append(o.getStock()).append("/");
        }

        return s.toString();
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

    public void decreaseProductStock(Product p, int toDecrease){
        OfferAndStock tuple = this.offers.get(p);
        tuple.setStock(tuple.getStock() - toDecrease);
        this.offers.put(p, tuple);
    }

    public OfferAndStock getOfferByProduct(Product p){
        return this.offers.get(p);
    }

    public BigDecimal getPrice(Product p){
        if(offers.get(p) == null)
            return new BigDecimal(0);
        return offers.get(p).getPrice();
    }

    public String getName() {
        return name;
    }


}
