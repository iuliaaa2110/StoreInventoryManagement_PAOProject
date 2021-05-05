package TheEntireSystem;

import java.math.BigDecimal;
import java.util.HashMap;

public class StoreHouse {

    int maxTotalStock = 3000;
    protected StockManagement mainStock;
    protected BigDecimal mainBank;
    private static StoreHouse instance = null;

    private StoreHouse(StockManagement mainStock, BigDecimal mainBank) {
        this.mainStock = mainStock;
        this.mainBank = mainBank;
    }

    public static StoreHouse getInstance(StockManagement mainStock, BigDecimal mainBank) {
        if (instance == null)
            instance = new StoreHouse(mainStock, mainBank);
        return instance;
    }
    public BigDecimal getMainBank() {
        return mainBank;
    }

    protected void collects(BigDecimal gain) {
        this.mainBank = this.mainBank.add(gain);
    }

    public int getMaxTotalStock() {
        return maxTotalStock;
    }

    public void setMaxTotalStock(int maxTotalStock) {
        this.maxTotalStock = maxTotalStock;
    }
}