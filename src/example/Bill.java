package example;

import java.util.ArrayList;
import java.util.List;

public class Bill {
    private int id;
    private String date;
    private List<Fruit> fruits;

    public Bill(String date) {
        this.date = date;
        fruits = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    public double getTotal(){
        double total = 0.00;
        if(!fruits.isEmpty()){
            for(Fruit fruit : fruits){
                total += fruit.getQuantity()*fruit.getPrice();
            }
        }
        return total;
    }
}
