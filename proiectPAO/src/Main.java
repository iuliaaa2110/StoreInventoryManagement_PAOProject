import java.time.format.DateTimeFormatter;
//import src.StoreHouse
//import print

public class Main {
    public static void main(String[] args) {

        //Serviciul 1: vezi stocul actual din depozit (getMainStock)
        StoreHouse ob = StoreHouse.getInstance();
        System.out.println(ob.getMainStock());

//        System.out.println(ob.x);
//        StoreHouse ob2 = StoreHouse.getInstance();
//        ob2.x = 7;
//        System.out.println(ob.x);

//    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
//    Product produs = new Product(new String("Mozzarela"),2021, 10/03/2021);
//    system.out.println(produs.expDate)
    }
}
