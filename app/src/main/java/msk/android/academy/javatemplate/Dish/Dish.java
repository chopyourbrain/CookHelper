package msk.android.academy.javatemplate.Dish;

import java.util.List;

import msk.android.academy.javatemplate.Product;

public class Dish {
    private int id;
    private String name;
    private String url;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String imageUrl;
    private int persons;
    private String time;
    private List<Product> products;

    public Dish(int id, String name, String url, int persons) {
        this.id = id;
        this.name = name;
        this.persons = persons;
        this.url = url;
        //Достаем продукты
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
