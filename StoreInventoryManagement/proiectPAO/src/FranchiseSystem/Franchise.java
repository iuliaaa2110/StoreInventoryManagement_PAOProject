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


        this.providers = read.readProviders();
        this.franchisePoints = read.readStores();
        this.storeHouse = read.readStoreHouse();
    }

    public static Franchise getInstance() {
        if (instance == null)
            instance = new Franchise();
        return instance;
    }

    //Overloading
    public String toString() {
        StringBuilder s = new StringBuilder();

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

    //Services:
    protected void refillProductStock(Store store, String p){
        this.refillProductStock(store, p, store.getRegularStockSize());
    }

    protected void refillProductStock(Store store, String productName, int desiredQuantity){
        Product shp = storeHouse.getProductByName(productName);
        Product p = store.getProductByName(productName);

        if(shp == null) {
            System.out.println("This product is not in the main stock. Try to order it from a provider first.");
        }
        else if(store.storeStock.isFull(store.getMaxTotalStockSize())){
            System.out.println("No more free space in the store for bringing other items :( .");
        }
        else{
            // daca nu exista acest produs in store, il adaug
            if(p == null) {
                p = new Product(shp);
                store.storeStock.updateStock(p, 0);
            }

            Integer currentQuantity;
            Integer availableQuantity;

            currentQuantity = store.storeStock.getProductStock(p);
            Integer neededQuantity = desiredQuantity - currentQuantity;
            availableQuantity = storeHouse.mainStock.getProductStock(shp);

            if (neededQuantity == 0) {
                System.out.println("Stock already full.");
            }
            else if(neededQuantity + store.storeStock.actualStockSize() > store.getMaxTotalStockSize()) {
                    System.out.println("Your requirement exceeds the capacity of the store stock.");
                }
            else {
                if (availableQuantity >= neededQuantity) {

                    store.storeStock.updateStock(p, desiredQuantity);
                    storeHouse.mainStock.updateStock(shp, availableQuantity - neededQuantity);

                    System.out.println("The stock of " + p.getProductName() + " succesfully refilled at " + store.address + " store");

                } else {

                    store.storeStock.updateStock(p, currentQuantity + availableQuantity);
                    storeHouse.mainStock.updateStock(shp, 0);

                    System.out.println("Could only be added " + availableQuantity + " pieces. The main stock (from the StoreHouse) of " + p.getProductName() + " is empty now.");
                }

                // daca au preturi diferite inseamna ori ca furnizorul si-a schimbat oferta, ori ca am comandat de la alt
                // furnizor decat ultima data
                // Daca acum l-am cumparat mai scump o sa crestem pretul pentru produs (inclusiv pt lotul vechi).
                // Daca l-am cumparat la acelasi pret sau mai ieftin, pretul de iesire ramane neschimbat
                // nu putem sa avem acelasi produs in magazin cu preturi amestsecate.
                if(shp.getProviderPrice().compareTo(p.getProviderPrice()) > 0) {
                    System.out.println("\nIn the meantime this product's offer has changed. We will sell it at a new price from now");
                    p.setProviderPrice(new BigDecimal(String.valueOf(shp.getProviderPrice())));
                    p.calculateOutprice();
                }

            }
        }
    }

    // trimite banii incasati la depozitul central (storehouse)
    protected void clearBank(Store store){
        storeHouse.collects(store.storeBank);
        store.storeBank = new BigDecimal(0);
    }

    void provide(Provider provider, String productName, Integer neededQuantity) {

        Product p = storeHouse.getProductByName(productName);
        OfferAndStock offer = provider.getOfferByProduct(productName);

        // daca nu exista acest produs in storehouse, il adaug
        if(p == null) {
            p = new Product(productName, new BigDecimal(0));
            storeHouse.mainStock.updateStock(p, 0);
        }

        if (!storeHouse.mainStock.isFull(storeHouse.getMaxTotalStock())) {

            if (offer == null)
                System.out.println("This provider cannot help you this time :( !!");
            else {
                
                Integer currentQuantity = storeHouse.mainStock.getProductStock(p);
                Integer availableQuantity = offer.getStock();

                if (availableQuantity >= neededQuantity) {

                    storeHouse.mainStock.updateStock(p, currentQuantity + neededQuantity);
                    provider.decreaseProductStock(productName, neededQuantity);
                    storeHouse.mainBank = storeHouse.mainBank.subtract(offer.getPrice().multiply(new BigDecimal(neededQuantity)));
                    System.out.println("The order of " + p.getProductName() + " successfully fulfilled.");

                } else {

                    storeHouse.mainStock.updateStock(p, currentQuantity + availableQuantity);
                    provider.decreaseProductStock(productName, availableQuantity);
                    storeHouse.mainBank = storeHouse.mainBank.subtract(offer.getPrice().multiply(new BigDecimal(availableQuantity)));
                    System.out.println("Could only get " + availableQuantity + " pieces of " + p.getProductName() +
                            ". The provider stock of" +
                            p.getProductName() + " is empty now.");
                }

                if(offer.getPrice().compareTo(p.getProviderPrice()) > 0) {
                    System.out.println("This product's offer has changed. We will sell it at a new price from now");
                    p.setProviderPrice(offer.getPrice());
                    p.calculateOutprice();
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
    protected void chooseProvider(String p) {

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

    public void UpdateCSV(){
        write.writeStorehouse(storeHouse);
        write.writeProviders(providers);
        write.writePoints(franchisePoints);
    }

    Store getStoreById(int nr){
        return franchisePoints.get(nr);
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

}
