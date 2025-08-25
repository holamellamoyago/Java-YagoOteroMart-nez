package Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Model.Producto;

public class ReadWriteList {
    static File file = new File("Repository/Products.bin");

    public static List<Producto> readProducts(){
        List<Producto> productos;

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            productos = (List<Producto>) in.readObject();

            in.close();
            return productos;
        } catch (IOException | ClassNotFoundException e ) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void writeProducts(List<Producto> products){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(products);

            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Producto> deleteProduct(Producto product){
        return new ArrayList<>();
    }
}
