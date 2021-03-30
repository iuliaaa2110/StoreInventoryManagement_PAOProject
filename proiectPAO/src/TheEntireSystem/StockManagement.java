package TheEntireSystem;

import java.util.HashMap;
import java.util.Map;

public class StockManagement {

    private Map<Product, Integer> stock = new HashMap<>();

    protected StockManagement(){
        this.stock = new HashMap<>();
    }

    protected StockManagement(Map<Product, Integer> stock) {
        this.stock = stock;
    }

    protected  Map <Product, Integer> getStock(){

        return stock;
    }

    // either add new pair (if the product does not exist in the stock), either modify the quantity of the product
    protected void updateStock(Product p, Integer quantity){
        this.stock.put(p, quantity);
    }

    protected Integer getProductStock(Product p){
        return this.stock.get(p);
    }

    protected Boolean isFull(int max){
        Integer s = stock.values().stream().reduce(0, Integer::sum);
        return s == max;
    }

    protected Boolean isEmpty(){
        if(stock == null)
            return true;

        Integer s = stock.values().stream().reduce(0, Integer::sum);

        return s == 0;
    }

}
