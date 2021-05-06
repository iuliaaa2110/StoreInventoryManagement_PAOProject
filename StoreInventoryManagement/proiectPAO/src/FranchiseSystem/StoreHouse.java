package FranchiseSystem;

import java.math.BigDecimal;

public class StoreHouse {

    int maxTotalStock = 3000;
    protected StockManagement mainStock;
    protected BigDecimal mainBank;
    private static StoreHouse instance = null;
    String stockManagementCSV;

    private StoreHouse(StockManagement mainStock, BigDecimal mainBank, String stockManagementCSV) {
        this.mainStock = mainStock;
        this.mainBank = mainBank;
        this.stockManagementCSV = stockManagementCSV;
    }

    public static StoreHouse getInstance(StockManagement mainStock, BigDecimal mainBank, String stockManagementCSV) {
        if (instance == null)
            instance = new StoreHouse(mainStock, mainBank, stockManagementCSV);
        return instance;
    }

    public String Columns(){
        return mainBank + "," + stockManagementCSV;
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
}