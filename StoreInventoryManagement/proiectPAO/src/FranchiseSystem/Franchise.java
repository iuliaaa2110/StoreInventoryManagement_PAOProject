package FranchiseSystem;

import java.math.BigDecimal;
import java.util.*;

import ExternalComponents.OfferAndStock;
import ExternalComponents.Provider;
import IO.Read;
import IO.Write;

public class Franchise {
    private final ArrayList<Store> franchisePoints;
    private final StoreHouse storeHouse;

    private final ArrayList<Provider> providers;
    private final ArrayList<Product> products;

    private static Franchise instance = null;

//    FranchiseInit franchiseInit = new FranchiseInit();
    Read read = Read.getInstance();
    Write write = Write.getInstance();


    // Constructors
    private Franchise() {

//         this.products = franchiseInit.initProducts();
//         this.providers = franchiseInit.initProviders(products);
//         this.franchisePoints  = franchiseInit.initFranchisePoints(products);
//        this.storeHouse = franchiseInit.initStoreHouse(providers, products);

        this.products = read.readProducts();
        this.providers = read.readProviders(products);
        this.franchisePoints = read.readStores(products);
        this.storeHouse = read.readStoreHouse(products);
    }

    public static Franchise getInstance() {
        if (instance == null)
            instance = new Franchise();
        return instance;
    }

    //Overloading
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("\nProducts:\n\n");
        for (int i = 0; i < products.size(); i ++) {
            s.append(i).append(".").append(products.get(i)).append("; ");
        }

        s.append("\n\nProviders:\n\n");
        for (int i = 0; i < providers.size(); i ++) {
            s.append(i).append(".").append(providers.get(i)).append(",\n ");
        }

        s.append("\n\nStores:\n\n");
        for (int i = 0; i < franchisePoints.size(); i ++) {
            s.append(i).append(".").append(franchisePoints.get(i)).append('\n');
        }

        s.append('\n');

        return s.toString();
    }

    // Utils:
    BigDecimal calculateAddition(BigDecimal initialPrice){
        return initialPrice.multiply(new BigDecimal(7)).divide(new BigDecimal(100));   // addition price = 7%
    }

    //Services:
    protected void refillProductStock(Store store, Product p){
        this.refillProductStock(store, p, store.getRegularStockSize());
    }

    protected void refillProductStock(Store store, Product p, int desiredQuantity){

        //doar daca in stock ul magazinului mai e loc
//        if(desiredQuantity + store.storeStock.actualStockSize() > store.getMaxTotalStockSize())
//            System.out.println("Your requirement exceeds the capacity of the store stock. Trying to add jus");
//
        if(!store.storeStock.isFull(store.getMaxTotalStockSize())) {

            Integer currentQuantity;
            Integer availableQuantity;

            if(store.storeStock.getProductStock(p) == 0)
                currentQuantity = 0;
            else
                currentQuantity = store.storeStock.getProductStock(p);

            Integer neededQuantity = desiredQuantity - currentQuantity;

            if(storeHouse.mainStock.getProductStock(p) == 0)
                availableQuantity= 0;
            else
                availableQuantity = storeHouse.mainStock.getProductStock(p);

            if (neededQuantity == 0) {
                System.out.println("Stock already full.");
            }
            else if(neededQuantity + store.storeStock.actualStockSize() > store.getMaxTotalStockSize()) {
//                    neededQuantity = store.getMaxTotalStockSize() - store.storeStock.actualStockSize();
                    System.out.println("Your requirement exceeds the capacity of the store stock.");
                }
            else {
                if (availableQuantity >= neededQuantity) {

                    store.storeStock.updateStock(p, desiredQuantity);
                    storeHouse.mainStock.updateStock(p, availableQuantity - neededQuantity);

                    System.out.println("The stock of " + p.getProductName() + " succesfully refilled at " + store.address + " store");

                } else {

                    store.storeStock.updateStock(p, currentQuantity + availableQuantity);
                    storeHouse.mainStock.updateStock(p, 0);

                    System.out.println("Could only be added " + availableQuantity + " pieces. The main stock (from the StoreHouse) of " + p.getProductName() + " is empty now.");
                }
            }
        }
        else
            System.out.println("No more free space in the store for bringing other items :( .");
    }

    // trimite banii incasati la depozitul central (storehouse)
    protected void clearBank(Store store){
        storeHouse.collects(store.storeBank);
        store.storeBank = new BigDecimal(0);
    }

    void provide(Provider provider, Product p, Integer neededQuantity) {

        if (!storeHouse.mainStock.isFull(storeHouse.getMaxTotalStock())) {

            if (provider.getOfferByProduct(p) == null)
                System.out.println("This provider cannot help you this time :( !!");
            else {
                OfferAndStock tuple = provider.getOfferByProduct(p);

                Integer currentQuantity = storeHouse.mainStock.getProductStock(p);
                Integer availableQuantity = tuple.getStock();

                if (availableQuantity >= neededQuantity) {

                    storeHouse.mainStock.updateStock(p, currentQuantity + neededQuantity);
                    provider.decreaseProductStock(p, neededQuantity);

                    storeHouse.mainBank = storeHouse.mainBank.subtract(tuple.getPrice().multiply( new BigDecimal(neededQuantity)));

                    //product needs the provider price so it can calculate the storePrice for its own
                    p.setProviderPrice(tuple.getPrice());

                    System.out.println("The order of " + p.getProductName() + " successfully fulfilled.");

                } else {

                    storeHouse.mainStock.updateStock(p, currentQuantity + availableQuantity);
                    provider.decreaseProductStock(p, availableQuantity);
                    storeHouse.mainBank = storeHouse.mainBank.subtract( tuple.getPrice().multiply( new BigDecimal( availableQuantity)));

                    p.setProviderPrice(tuple.getPrice());

                    System.out.println("Could only get " + availableQuantity + " pieces of " + p.getProductName() +
                            ". The provider stock of" +
                            p.getProductName() + " is empty now.");
                }
            }
        }
    }

    //   Show actual stock from the storeHouse
    protected void getStoreHouseStock (){
        System.out.println("Actual stock of the storeHouse is " + storeHouse.mainStock.getStock() + " . \n");
    }

    //  Show actual stock of a store
    protected void getStoreStock(int nr){
        System.out.println("You chose " + franchisePoints.get(nr).getAddress() + " store");
        System.out.println("Actual stock of " +  franchisePoints.get(nr).getAddress() + " is " + franchisePoints.get(nr).storeStock.getStock() + " .\n");
    }

    // Show the bank's gain at the storeHouse
    protected void getStoreHouseGain(){
        System.out.println("StoreHouse's bank = " + storeHouse.getMainBank());

    }

    // Show the bank's gain of a store
    protected void getStoreGain(int nr){
        System.out.println("The amount of money at the store right now is: " + franchisePoints.get(nr).getStoreBank() + " . \n");
    }

    // Send the money from a store's bank to the StoreHouse's bank
    protected void collectMoney(Store store){

        System.out.println("Store's bank before: " + store.getStoreBank());
        System.out.println("StoreHouse's bank before: " + storeHouse.getMainBank());

        clearBank(store);

        System.out.println("Store's bank now: " + store.getStoreBank());
        System.out.println("StoreHouse's bank now: " + storeHouse.getMainBank());

    }

    // cand nu mai avem in depozit un produs si vrem sa comandam de la un furnizor,
    // iteram prin ofertele furnizorilor si il alegem pe cel care vinde produsul mai ieftin
    // find the provider with the best price for a product
    protected void chooseProvider(Product p) {

        PriorityQueue<Map.Entry<BigDecimal, Provider>> pq = new PriorityQueue<>(Map.Entry.comparingByKey());

        for (Provider provider : providers)
            if (!provider.getPrice(p).equals(new BigDecimal(0)))
                pq.offer(new AbstractMap.SimpleEntry<>(provider.getPrice(p), provider));

        if (pq.isEmpty())
            System.out.println("There are no such things in the whole country!! everyone is sold out on this product");
        else {
            assert pq.peek() != null;

            Provider chooseProvider = pq.peek().getValue();

            System.out.println(chooseProvider.getName());
        }
    }

    // set the outprice for a product (calculates with a default addition of 7%)
    // see the entry price and the out price of a product
    protected void setOutprice(Product product){

        if(product.getStorePrice() != null)
            System.out.println("entryPrice = " + product.getProviderPrice() + "\noutPrice = "
                    + product.getStorePrice());
        else
        {
            if(product.getProviderPrice() == null)
                System.out.println("You have never ordered this product before so we don't have it in our database. " +
                        "Find a provider at Service7 and order the product at Service 8. ");
            else
            {
                BigDecimal providerPrice = product.getProviderPrice();
                BigDecimal outprice = providerPrice.add(calculateAddition(providerPrice));
                product.setStorePrice(outprice);

                System.out.println("entryPrice = " + product.getProviderPrice() + "\noutPrice = "
                        + product.getStorePrice());
            }
        }
    }

    public void UpdateCSV(){
        write.writeProducts(products);
        write.writeStorehouse(storeHouse);
        write.writeProviders(providers);
        write.writePoints(franchisePoints);
    }

    Store getStoreById(int nr){
        return franchisePoints.get(nr);
    }

    Product getProductById(int nr){
        return products.get(nr);
    }

    Product getProductById(String name){
        int i;

        Product p = new Product(name);

        if(products.contains(p)){
            i = products.indexOf(p);
            return products.get(i);
        }

        return null;
    }

    Provider getProviderById(int nr){return providers.get(nr); }

    Provider getProviderById(String name){
        int i;

        Provider p = new Provider(name);

        if(providers.contains(p)){
            i = providers.indexOf(p);
            return providers.get(i);
        }

        return null; }

    int getPointsNumber(){
        return franchisePoints.size();
    }

}
