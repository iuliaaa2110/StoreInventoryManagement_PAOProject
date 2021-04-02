package TheEntireSystem;

import TheEntireSystem.Company;

import java.util.Scanner;
import java.math.BigDecimal;
import java.util.*;

public class Service {

    Company company = new Company();


    //   Show actual stock from the storeHouse
    public void Service1 (){
        company.Service1();
    }

    //  Show actual stock of a store
    public void Service2(){
        company.Service2();
    }

    // Show the bank's gain at the company.storeHouse
    public void Service3(){

        company.Service3();

    }

    // Show the bank's gain of a store
    public void Service4(){
        company.Service4();
    }

    // Send the money from a store's bank to the StoreHouse's bank
    public void Service5(){

        company.Service5();

    }

    // Refill the stock at a store for specific company.products (with the regular quantity or with a specific quantity)
    public void Service6(){

        company.Service6();
    }


    // cand nu mai avem in depozit un produs si vrem sa comandam de la un furnizor,
    // iteram prin ofertele furnizorilor si il alegem pe cel care vinde produsul mai ieftin
    // find the provider with the best price for a product
    public void Service7() {
        company.Service7();
    }


    // make an order to a provider
    public void Service8(){
        company.Service8();

    }

    // make a sell
    // if the client buys a significantly amount of items from a Supermarket he gets a discount
    public void Service9(){

        company.Service9();

    }

    //see the entry price and the out price of a product
    public void Service10(){

        company.Service10();
    }

    public void getService() {

        boolean stop = false;

        while (!stop){
            System.out.println("\n~Enter the service you want to use: ");
            Scanner keyboard = new Scanner(System.in);
            String serviceNumber = keyboard.nextLine();
            try{
                switch (serviceNumber) {
                    case "Service1" : Service1();break;
                    case "Service2" : Service2();break;
                    case "Service3" : Service3();break;
                    case "Service4" : Service4();break;
                    case "Service5" : Service5();break;
                    case "Service6" : Service6();break;
                    case "Service7" : Service7();break;
                    case "Service8" : Service8();break;
                    case "Service9" : Service9();break;
                    case "Service10" : Service10();break;

                    case "stop" : stop = true;

                }
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }

}