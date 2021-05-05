package TheEntireSystem;

import java.math.BigDecimal;
import java.util.HashMap;

public class StoreHouse {

    protected StockManagement mainStock;
    protected BigDecimal mainBank;

    public StoreHouse(StockManagement mainStock, BigDecimal mainBank) {
        this.mainStock = mainStock;
        this.mainBank = mainBank;
    }

    public BigDecimal getMainBank() {
        return mainBank;
    }

    protected void collects( BigDecimal gain) {
        this.mainBank = this.mainBank.add(gain);
    }
}