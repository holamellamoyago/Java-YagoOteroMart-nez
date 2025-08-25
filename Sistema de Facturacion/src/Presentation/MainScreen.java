package Presentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.control.Button;


public class MainScreen implements Initializable {

    @FXML
    private ListView<Producto> listAvaiables , listViewFinal;

    @FXML
    private Text txtCuantityProduct;

    @FXML
    private Text txtNameProduct;

    @FXML
    private Button btnAddProduct;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadProducts();
    }

    @SuppressWarnings("unchecked")
    private void loadProducts() {
        String url = "src/Repository/Productos.bin";
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(url));
            List<Producto> products = (List<Producto>)in.readObject();
            listAvaiables.setItems(FXCollections.observableArrayList(products));
            in.close();

        } catch (FileNotFoundException e) {
            createFolderRepository();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void createFolderRepository() {
        File carpeta = new File("src/Repository");
        if (!carpeta.exists()) {
            carpeta.mkdir();
            loadProducts();
        }
        listAvaiables.setItems(FXCollections.observableArrayList());
    }
}
