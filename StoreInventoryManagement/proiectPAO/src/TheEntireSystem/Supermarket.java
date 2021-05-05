package TheEntireSystem;

import java.math.BigDecimal;

public class Supermarket extends Store{


    private static final int regularStockSize = 70; // regular amount of stock per product
    private static final int maxTotalStockSize = 1000;

    public Supermarket(String address){
        super(address);
    }


    @Override
    public String toString() {
        return address + '\n' +
                ", storeStock=" + storeStock +
                ", regularStockSize=" + getRegularStockSize() +
                ", maxTotalStockSize=" + getMaxTotalStockSize() +
                ", storeBank=" + storeBank +
                "}\n";
    }

    @Override
    protected void Sell(Product p, Integer requiredQuantity){
        if(this.storeStock.getProductStock(p) == 0) {

            System.out.println("Sorry. We are sold out for this product: " + p.getProductName() +" . We will bring more of it soon!");
        }
        else {
            if(p.getStorePrice() == null)
                System.out.println("You need to refill the stock for this product first:" + p + " Try with Service 6.");
            else {
                Integer actualQuantity = this.storeStock.getProductStock(p);
                int soldQuantity;

                if (requiredQuantity <= actualQuantity) {
                    soldQuantity = requiredQuantity;

                    System.out.println("Successfully sold. ");
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

                this.storeStock.updateStock(p, -soldQuantity);
            }
        }
    }

    public static int getRegularStockSize() {
        return regularStockSize;
    }

    public static int getMaxTotalStockSize() {
        return maxTotalStockSize;
    }

}
