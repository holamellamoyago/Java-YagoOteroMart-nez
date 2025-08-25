package Model;

import java.io.Serializable;

public class Producto implements Serializable {
    String name;
    int cuantity;
    boolean avaiable;

    public Producto(String name, boolean avaiable, int cuantity) {
        this.name = name;
        this.cuantity = cuantity;
        this.avaiable = avaiable;
    }

    public Producto(String name) {
        this(name, true, 1);
    }

    @Override
    public String toString() {
        return name + " - " + cuantity;
    }

}
