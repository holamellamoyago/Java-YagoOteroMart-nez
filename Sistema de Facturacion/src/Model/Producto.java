package Model;

import java.io.Serializable;

public class Producto implements Serializable, Comparable<Producto> {
    private String name;
    private int cuantity;
    private double price;
    private boolean avaiable;

    public Producto(String name, boolean avaiable, int cuantity, double price) {
        this.name = name;
        this.cuantity = cuantity;
        this.avaiable = avaiable;
        this.price = price;
    }

    public Producto(String name) {
        this(name, true, 1, 1);
    }

    @Override
    public String toString() {
        return name + ", Cantidad: " + cuantity + ",precio: " + price;
    }

    @Override
    public int compareTo(Producto o) {
        return o.name.compareTo(name);
    }

    // Getters y Setters

    public String getName() {
        return name;
    }

    public int getCuantity() {
        return cuantity;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvaiable() {
        return avaiable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCuantity(int cuantity) {
        this.cuantity = cuantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvaiable(boolean avaiable) {
        this.avaiable = avaiable;
    }

}
