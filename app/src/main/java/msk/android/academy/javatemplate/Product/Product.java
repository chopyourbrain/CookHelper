package msk.android.academy.javatemplate.Product;

public class Product {
    private int id;
    private String name;
    private float weight;
    private String idDish;
    private float balance;
    private boolean check;

    public float getBalance() {
        return balance;
    }

    public void setBalanca(int balanca) {
        this.balance = balanca;
    }

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Product(String name, float weight, float balance, String idDish) {

        this.name = name;
        this.weight = weight;
        this.idDish = idDish;
        this.check = false;
        this.balance = balance;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getIdDish() {
        return idDish;
    }

    public void setIdDish(String idDish) {
        this.idDish = idDish;
    }
}
