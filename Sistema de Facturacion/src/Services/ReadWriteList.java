package Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Model.Producto;

public class ReadWriteList<T extends Serializable> {
    // static File file = new File("Repository/Products.bin");
    private String fileName;


    public ReadWriteList(String fileName) {
        this.fileName = "Repository/"+fileName + ".bin";
    }

    public List<T> readProducts(){
        List<T> productos;

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            productos = (List<T>) in.readObject();

            in.close();
            return productos;
        } catch (IOException | ClassNotFoundException e ) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void writeProducts(List<Producto> products){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(products);

            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public void deleteProduct(T element){
    //     List<Producto> products = readProducts();

    //     for (int i = 0; i < products.size(); i++) {
    //         if (products.get(i).equals(product)) {
    //             products.remove(i);
    //         }
    //     }

    //     writeProducts(products);
        
    // }
}
