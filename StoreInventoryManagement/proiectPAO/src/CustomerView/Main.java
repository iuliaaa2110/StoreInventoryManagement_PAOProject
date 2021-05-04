package CustomerView;

import TheEntireSystem.Service;
import TheEntireSystem.Store;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        service.getService();

        Store store = new Store("buna");
        Store store2 = store;

        System.out.println("store1 = " + store.getAddress());
        System.out.println("store2 = " + store2.getAddress());

        String address = store.getAddress();
        address = "deci";

        store2.setAddress("pa");

        System.out.println("store1 = " + store.getAddress());
        System.out.println("store2 = " + store2.getAddress());
    }
}
