import java.util.Map;

// Singleton class
public class StoreHouse {

    private static StoreHouse single_instance = null;

    // retin cate produse am din fiecare tip in depozit
    private Map<Product, Integer> mainStock;

    private StoreHouse(){

    }

    public static StoreHouse getInstance()
    {
        if (single_instance == null)
            single_instance = new StoreHouse();

        return single_instance;
    }

    protected Map<Product, Integer> getMainStock(){
        return mainStock;
    }

    protected void updateStockMain(){

    }
}
