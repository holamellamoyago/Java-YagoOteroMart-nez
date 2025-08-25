package Model;

import java.io.Serializable;

public class Producto implements Serializable {
    String name; 
    int stock;
    boolean avaiable;

    public Producto(String name, int stock, boolean avaiable) {
        this.name = name;
        this.stock = stock;
        this.avaiable = avaiable;
    }

    public Producto(String name, int stock){
        this(name, stock, true);
    }

    
}
