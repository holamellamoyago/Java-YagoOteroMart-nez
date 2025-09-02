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
        this.fileName = "Repository/" + fileName + ".bin";
    }

    public List<T> readProducts() {
        List<T> productos;

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            productos = (List<T>) in.readObject();

            in.close();
            return productos;
        } catch (IOException | ClassNotFoundException e) {
            
            return new ArrayList<>();
        }
    }

    public void writeProducts(List<T> products) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(products);

            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<T> deleteProduct(T element, List<T> elements) {

        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).equals(element)) {
                elements.remove(i);
            }
        }

        return elements;

    }
}
