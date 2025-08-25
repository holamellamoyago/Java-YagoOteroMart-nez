package Model;

import java.io.Serializable;

public class Producto implements Serializable {
    String name;
    boolean avaiable;

    public Producto(String name, boolean avaiable) {
        this.name = name;
        this.avaiable = avaiable;
    }

    public Producto(String name) {
        this(name, true);
    }

    @Override
    public String toString() {
        return "Producto [name=" + name + "]";
    }

    

}
