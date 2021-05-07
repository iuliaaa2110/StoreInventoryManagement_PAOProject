package FranchiseSystem;

import java.math.BigDecimal;

public class StoreHouse {

    int maxTotalStock = 3000;
    protected StockManagement mainStock;
    private BigDecimal mainBank;
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

    public String stockColumns(){
        return mainStock.Columns();
    }

    protected BigDecimal getMainBank() {
        return new BigDecimal(String.valueOf(mainBank));
    }

    protected void setMainBank(BigDecimal val) {
        this.mainBank = new BigDecimal(String.valueOf(val));
    }

    protected void collects(BigDecimal gain) {
        this.mainBank = this.mainBank.add(gain);
    }

    protected int getMaxTotalStock() {
        return maxTotalStock;
    }

    public String getStockManagementCSV() {
        return stockManagementCSV;
    }

    protected Product getProductByName(String name){
        return mainStock.getProductByName(name);
    }
}