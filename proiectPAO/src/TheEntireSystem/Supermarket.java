package TheEntireSystem;

import java.math.BigDecimal;

public class Supermarket extends Store{

    protected static int regularStock = 70; // regular amount of stock per product
    private final int maxTotalStock = 1000;

    public Supermarket(String address){
        super(address);
    }

    @Override
    public void Sell(Product p, Integer soldQuantity){
        if(this.storeStock.getProductStock(p) == 0) {

            System.out.println("Sorry. We are sold out for this product ( " + p.getProductName() + " ). We will bring more of it soon!");
        }
        else {

            // daca a cumparat un nr semnificativ de produse de acelasi tip primeste discount

            if(soldQuantity > 2 * regularStock / 3)
            {
                BigDecimal price = p.getStorePrice();
//
//              discount = 3 * price / 100;
                BigDecimal discount = price.multiply(new BigDecimal(3 / 100));

                BigDecimal newSellPrice = price.subtract(discount);

                this.storeBank = this.storeBank.add(newSellPrice);
                this.storeStock.updateStock(p, -soldQuantity);

                System.out.println("Good moment for you! You bought very many items so that we got you a 3% discount! Have a nice day :)");
            }
            else {
                this.storeBank = this.storeBank.add(p.getStorePrice());
                this.storeStock.updateStock(p, -soldQuantity);

                System.out.println("Successfully sold. ");
            }
        }
    }

}
