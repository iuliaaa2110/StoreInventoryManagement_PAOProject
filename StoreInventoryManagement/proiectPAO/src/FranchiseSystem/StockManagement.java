package FranchiseSystem;

import ExternalComponents.OfferAndStock;

import java.util.HashMap;
import java.util.Map;

public class StockManagement {

    private Map<Product, Integer> stock;

    public StockManagement(){
        this.stock = new HashMap<>();
    }

    public StockManagement(Map<Product, Integer> stock) {
        this.stock = stock;
        calculateOutprice(); // doar aici am voie sa am acces la outprice-uri, nu si in read.
    }

    Integer stockSize = 0;

    @Override
    public String toString() {
        return "StockManagement {" +
                "stock=" + stock +
                '}';
    }

    public String Columns() {
        StringBuilder s = new StringBuilder();

        for(Map.Entry<Product, Integer> entry : stock.entrySet()){
            Product p = entry.getKey();
            Integer quantity = entry.getValue();
            s.append(p.getProductName()).append(",").append(p.getProviderPrice()).append(",").append(quantity).append("\n");
        }

        return s.toString();
    }

    // either add new pair (if the product does not exist in the stock), either modify the quantity of the product
    protected void updateStock(Product p, Integer quantity){
        this.stock.put(p, quantity);
    }

    protected Integer getProductStock(Product p){
        if (stock.isEmpty())
            return 0;
        if (this.stock.get(p) == null)
            return 0;
        return this.stock.get(p);
    }

    protected Integer actualStockSize(){
        return stock.values().stream().reduce(0, Integer::sum);
    }

    protected Boolean isFull(int max){
        return actualStockSize() == max;
    }

    protected Boolean isEmpty(){
        if(stock == null)
            return true;

        Integer s = stock.values().stream().reduce(0, Integer::sum);

        return s == 0;
    }

    protected Product getProductByName(String name){

        Product found = null;

        for (Map.Entry<Product, Integer> e : stock.entrySet()) {
            Product product   = e.getKey();
            Integer quantity = e.getValue();

            if(product.getProductName().equals(name))
            {
                found = product;
                break;
            }
        }

        return found;
    }

    private void calculateOutprice(){
        for(Map.Entry<Product, Integer> entry : stock.entrySet()){
            Product p = entry.getKey();
            Integer quantity = entry.getValue();

            if(p.getStorePrice() == null)
                p.calculateOutprice();
        }
    }

    protected  Map <Product, Integer> getStock(){
        return stock;
    }

}
