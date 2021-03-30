package TheEntireSystem;

import java.math.BigDecimal;

// Singleton class
public class StoreHouse {

    private static StoreHouse single_instance = null;
    private Boolean x = false;
    protected StockManagement mainStock;
    private BigDecimal mainBank;
    private final int maxTotalStock = 3000;

    public static StoreHouse getInstance() {

        if (single_instance == null)
            single_instance = new StoreHouse();

        return single_instance;
    }

    // va fi initializat o singura data!
    protected void setMainStock(StockManagement initializeStock) {

        mainBank = new BigDecimal(50); // default start

        if(!x)
            this.mainStock = initializeStock;
        x = true;
    }

    protected void provide(Provider provider, Product p, Integer neededQuantity) {

        if (!this.mainStock.isFull(maxTotalStock)) {

            OfferAndStock tuple = provider.offers.get(p);

            if (tuple == null)
                System.out.println("This provider cannot help you this time :( !!");
            else {
                Integer currentQuantity = this.mainStock.getProductStock(p);
                Integer availableQuantity = tuple.getStock();

                if (availableQuantity >= neededQuantity) {

                    this.mainStock.updateStock(p, currentQuantity + neededQuantity);
                    provider.decreaseProductStock(p, neededQuantity);

                    // traducere :)) :
                    // mainBank -= (tuple.price * neededQuantity)
                    this.mainBank = this.mainBank.subtract(tuple.getPrice().multiply( new BigDecimal(neededQuantity)));

                    //product needs the provider price so it can calculate the storePrice for its own
                    p.setProviderPrice(tuple.getPrice());

                    System.out.println("The order of " + p.getProductName() + " successfully fulfilled.");

                } else {

                    this.mainStock.updateStock(p, currentQuantity + availableQuantity);
                    provider.decreaseProductStock(p, availableQuantity);
                    this.mainBank = this.mainBank.subtract( tuple.getPrice().multiply( new BigDecimal( availableQuantity)));

                    p.setProviderPrice(tuple.getPrice());

                    System.out.println("Could only get " + availableQuantity + " pieces of " + p.getProductName() +
                            "No more stuff like this in the country!! The provider stock of" +
                            p.getProductName() + " is empty now.");
                }
            }
        }
    }

    public BigDecimal getMainBank() {
        return mainBank;
    }


    // incaseaza bani
    protected void collects( BigDecimal gain) {
        this.mainBank = this.mainBank.add(gain);
    }
}