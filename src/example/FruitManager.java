package example;

import java.text.SimpleDateFormat;
import java.util.*;

public class FruitManager {
    private static final Scanner INPUT = new Scanner(System.in);
    private List<Fruit> fruits;
    private Hashtable<String, List<Bill>> orders;

    public FruitManager() {
        fruits = new ArrayList<>();
        orders = new Hashtable<>();
    }

    public List<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    public Hashtable<String, List<Bill>> getOrders() {
        return orders;
    }

    public void setOrders(Hashtable<String, List<Bill>> orders) {
        this.orders = orders;
    }

    public void addFruit(){
        do {
            String id;
            do {
                System.out.print("Enter fruit' ID: ");
                id = INPUT.next();
                INPUT.nextLine();
                if(id.trim().isEmpty()){
                    System.out.println("Please enter fruit' name!");
                }
            }while(id.trim().isEmpty());

            boolean found = false;
            if(!fruits.isEmpty()){
                for(Fruit fruit : fruits){
                    if(fruit.getId().equalsIgnoreCase(id)){
                        System.out.println("Fruit's ID is existed!");
                        found = true;
                        break;
                    }
                }
            }
            if(found){
                continue;
            }

            String name;
            do {
                System.out.print("Enter fruit's name: ");
                name = INPUT.nextLine();
                if(name.trim().isEmpty()){
                    System.out.println("Please enter fruit's name!");
                }
            }while(name.trim().isEmpty());

            double price;
            do {
                try {
                    System.out.print("Enter fruit's price: ");
                    price = INPUT.nextDouble();
                    INPUT.nextLine();
                }
                catch (Exception e){
                    INPUT.nextLine();
                    price = 0;
                    System.out.println("Fruit's price is not correct format!");
                }
                if(price <= 0){
                    System.out.println("Fruit's price must be a positive digit!");
                }
            }while (price <= 0);

            int quantity;
            do {
                try {
                    System.out.print("Enter fruit's quantity: ");
                    quantity = INPUT.nextInt();
                    INPUT.nextLine();
                }
                catch (Exception e){
                    INPUT.nextLine();
                    quantity = 0;
                    System.out.println("Fruit's quantity is not correct format!");
                }
                if(quantity <= 0){
                    System.out.println("Fruit's quantity must be a positive digit!");
                }
            }while (quantity <= 0);
            String origin;
            do {
                System.out.print("Enter fruit's origin: ");
                origin = INPUT.nextLine();
                if(origin.trim().isEmpty()){
                    System.out.println("Please enter fruit's origin!");
                }
            }while(origin.trim().isEmpty());

            Fruit fruit = new Fruit(id,name,price,quantity,origin);
            fruits.add(fruit);

            String choose;
            do {
                System.out.print("Do you want to continue? (y/n): ");
                choose = INPUT.next();
                INPUT.nextLine();
                if(!choose.equalsIgnoreCase("y") && !choose.equalsIgnoreCase("n")){
                    System.out.println("Please choose y or n!");
                }
            }while (!choose.equalsIgnoreCase("y") && !choose.equalsIgnoreCase("n"));
            if (choose.equalsIgnoreCase("n")){
                break;
            }
        }while(true);
    }

    public void order(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String billDate = simpleDateFormat.format(date);
        Bill bill = new Bill(billDate);
        do {
            for(int i = 0; i < fruits.size(); i++){
                System.out.println((i + 1) + "." + "  " + fruits.get(i));
            }
            int chooseFruit;
            do {
                try {
                    System.out.print("Choose fruit: ");
                    chooseFruit = INPUT.nextInt();
                    INPUT.nextLine();
                }
                catch (Exception e){
                    INPUT.nextLine();
                    chooseFruit = 0;
                    System.out.println("Fruit's quantity is not correct format!");
                }
                if(chooseFruit <= 0 || chooseFruit >= fruits.size()){
                    System.out.println("Please choose fruit!");
                }
            }while (chooseFruit <= 0 || chooseFruit >= fruits.size());

            Fruit fruit = fruits.get(chooseFruit - 1);

            int quantity;
            do {
                try {
                    System.out.print("Choose fruit's quantity: ");
                    quantity = INPUT.nextInt();
                    INPUT.nextLine();
                }
                catch (Exception e){
                    INPUT.nextLine();
                    quantity = 0;
                    System.out.println("Fruit's quantity is not correct format!");
                }
                if(quantity <= 0){
                    System.out.println("Please choose fruit's quantity!");
                }
                if(quantity > fruit.getQuantity()){
                    System.out.println("Your selected quantity exceeded fruit's quantity.");
                }
            }while (quantity <= 0 || quantity > fruit.getQuantity());

            fruit.setQuantity(quantity);
            bill.getFruits().add(fruit);
            for(Fruit fruit1 : bill.getFruits()){
                System.out.println(fruit1);
            }
            System.out.print("Total: ");
            System.out.println(bill.getTotal());
            String choose;
            do {
                System.out.print("Do you want to continue? (y/n): ");
                choose = INPUT.next();
                INPUT.nextLine();
                if(!choose.equalsIgnoreCase("y") && !choose.equalsIgnoreCase("n")){
                    System.out.println("Please choose y or n!");
                }
            }while (!choose.equalsIgnoreCase("y") && !choose.equalsIgnoreCase("n"));
            if (choose.equalsIgnoreCase("n")){
                break;
            }
        }while(true);

        String name;
        do {
            System.out.print("Enter your name: ");
            name = INPUT.nextLine();
            if(name.trim().isEmpty()){
                System.out.println("Please enter your name!");
            }
        }while(name.trim().isEmpty());

        if(orders.containsKey(name)){
            orders.get(name).add(bill);
        }
        else{
            List<Bill> bills = new ArrayList<>();
            bills.add(bill);
            orders.put(name, bills);
        }
    }

    public void printOrders(){
        if(!orders.isEmpty()){
            Set<String> keys = orders.keySet();
            for (String key : keys){
                List<Bill> bills = orders.get(key);
                System.out.println(key);
                for(Bill bill : bills){
                    System.out.println(bill.getDate() + ":");
                    for(Fruit fruit : bill.getFruits()){
                        System.out.println(fruit);
                    }
                    System.out.println("Total: " + bill.getTotal());
                }
            }
        }
        else{
            System.out.println("Order list is empty.");
        }
    }
}
