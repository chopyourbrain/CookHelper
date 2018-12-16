package msk.android.academy.javatemplate.Product;

public class Product {
    private int id;
    private String name;
    private String weight;
    private String idDish;
    private int balance;
    private boolean check;

    public int getBalance() {
        return balance;
    }

    public void setBalanca(int balanca) {
        this.balance = balanca;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Product(String name, String weight, String idDish) {

        this.name = name;
        this.weight = weight;
        this.idDish = idDish;
        this.check = false;
        this.balance = 0;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getIdDish() {
        return idDish;
    }

    public void setIdDish(String idDish) {
        this.idDish = idDish;
    }
}
