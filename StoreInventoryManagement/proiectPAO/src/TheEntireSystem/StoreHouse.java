package TheEntireSystem;

import java.math.BigDecimal;
import java.util.HashMap;

public class StoreHouse {

    protected StockManagement mainStock = new StockManagement(new HashMap<Product, Integer>()){};
    protected BigDecimal mainBank;

//    public StoreHouse(){};

    public StoreHouse(StockManagement mainStock, BigDecimal mainBank) {
        this.mainStock = mainStock;
        this.mainBank = mainBank;
    }

    public BigDecimal getMainBank() {
        return mainBank;
    }


    // incaseaza bani
    protected void collects( BigDecimal gain) {
        this.mainBank = this.mainBank.add(gain);
    }
}