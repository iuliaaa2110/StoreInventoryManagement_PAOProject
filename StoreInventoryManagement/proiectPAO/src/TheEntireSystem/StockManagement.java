package TheEntireSystem;

import java.util.HashMap;
import java.util.Map;

public class StockManagement {

    private Map<Product, Integer> stock;

    public StockManagement(){
        this.stock = new HashMap<>();
    }

    public StockManagement(Map<Product, Integer> stock) {
        this.stock = stock;
    }

    Integer stockSize = 0;

    @Override
    public String toString() {
        return "StockManagement {" +
                "stock=" + stock +
                '}';
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

    protected  Map <Product, Integer> getStock(){
        return stock;
    }

}
