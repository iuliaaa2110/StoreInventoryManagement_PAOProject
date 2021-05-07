package FranchiseSystem;

import java.math.BigDecimal;

public class Supermarket extends Store{
    
    public Supermarket(String address){
        super(address);
        regularStockSize = 70;
        maxTotalStockSize = 1000;
    }
    public Supermarket(String address, BigDecimal storeBank, StockManagement storeStock, String stockManagemenCSV) {
        super(address, storeBank, storeStock, stockManagemenCSV);
        regularStockSize = 70;
        maxTotalStockSize = 1000;
    }

    @Override
    public String Columns(){
        return address + ",SP," + storeBank + "," + stockManagementCSV;
    }

    @Override
    protected void Sell(String productName, Integer requiredQuantity){
        Product p = getProductByName(productName);

        if(p == null || this.storeStock.getProductStock(p) == 0) {

            System.out.println("Sorry. We are sold out for this product: " + productName +" . We will bring more of it soon!");
        }
        else {
            if(p.getStorePrice() == null)
                System.out.println("You need to refill the stock for this product first:" + p + " Try with Service 6.");
            else {
                Integer actualQuantity = this.storeStock.getProductStock(p);
                int soldQuantity;

                if (requiredQuantity <= actualQuantity) {
                    soldQuantity = requiredQuantity;

                    System.out.println(productName + ": Successfully sold. ");
                } else {
                    soldQuantity = actualQuantity;

                    System.out.println("Could only sell " + actualQuantity + " pieces of " + p.getProductName() +
                            "                            \". The store stock of\" +\n" +
                            "                            p.getProductName() + \" is empty now.\"");
                }

                // daca a cumparat un nr semnificativ de produse de acelasi tip primeste discount

                if(soldQuantity > 2 * regularStockSize / 3) {
                    BigDecimal price = p.getStorePrice();
                    BigDecimal discount = price.multiply(new BigDecimal(3 / 100));

                    BigDecimal newSellPrice = price.subtract(discount);

                    this.storeBank = this.storeBank.add(newSellPrice.multiply(new BigDecimal(soldQuantity)));

                    System.out.println("Good moment for our client! He bought very many items, so that we got him a 3% discount! Have a nice day :)");
                }
                else
                    this.storeBank = this.storeBank.add(p.getStorePrice().multiply(new BigDecimal(soldQuantity)));

                this.storeStock.updateStock(p, actualQuantity-soldQuantity);
            }
        }
    }

    public int getRegularStockSize() {
        return regularStockSize;
    }

    public int getMaxTotalStockSize() {
        return maxTotalStockSize;
    }

}
