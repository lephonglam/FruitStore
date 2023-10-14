package example;

public class Main {
    public static void main(String[] args) {
        FruitManager fruitManager = new FruitManager();
        System.out.println("1. Add Fruit");
        fruitManager.addFruit();
        System.out.println("2. Order fruit");
        fruitManager.order();
        System.out.println("3. Print Orders");
        fruitManager.printOrders();
    }
}