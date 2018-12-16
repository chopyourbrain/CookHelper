package msk.android.academy.javatemplate.Product;

public class Product {
    private int id;
    private String name;
    private String weight;
    private String idDish;
    private boolean check;

    public Product(int id, String name, String weight, String idDish) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.idDish = idDish;
        this.check=false;
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
