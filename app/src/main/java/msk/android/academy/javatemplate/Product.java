package msk.android.academy.javatemplate;

public class Product {
    private int id;
    private String name;
    private String weight;
    private int idDish;

    public Product(int id, String name, String weight, int idDish) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.idDish = idDish;
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

    public int getIdDish() {
        return idDish;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }
}
