package CustomerView;

import TheEntireSystem.Service;
import IO.Write;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();

        boolean stop = false;

        service.showOptionsList();

        while (!stop){
            System.out.println("\n~Enter the service you want to use: ");
            Scanner keyboard = new Scanner(System.in);
            String operation = keyboard.nextLine();
            try{
                switch (operation) {
                    case "show_storehouse_stock" : service.show_storehouse_stock();break;
                    case "show_store_stock" : service.show_store_stock();break;
                    case "show_storehouse_bank" : service.show_storehouse_bank();break;
                    case "show_store_bank" : service.show_store_bank();break;
                    case "collect_gain" : service.collect_gain();break;
                    case "refill" : service.refill();break;
                    case "find_provider" : service.find_provider();break;
                    case "order" : service.order();break;
                    case "sell" : service.sell();break;
                    case "calculate_outprice" : service.calculate_outprice();break;

                    case "stop" : stop = true;

                }
                Write.writeAudit(operation);
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }
}

