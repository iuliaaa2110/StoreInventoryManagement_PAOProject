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
                    case "Service1" : service.Service1();break;
                    case "Service2" : service.Service2();break;
                    case "Service3" : service.Service3();break;
                    case "Service4" : service.Service4();break;
                    case "Service5" : service.Service5();break;
                    case "Service6" : service.Service6();break;
                    case "Service7" : service.Service7();break;
                    case "Service8" : service.Service8();break;
                    case "Service9" : service.Service9();break;
                    case "Service10" : service.Service10();break;

                    case "stop" : stop = true;

                }
                Write.writeAudit(operation);
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }
}

