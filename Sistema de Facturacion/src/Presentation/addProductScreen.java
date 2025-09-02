package Presentation;

import java.io.File;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import Model.Producto;
import Services.ReadWriteList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import Presentation.MainScreen;

public class addProductScreen {
    @FXML
    private Button btnAddPrroduct;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtFieldName;

    @FXML
    private Text txtError;

    private MainScreen mainScreen;

    public void setMainScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }

    @FXML
    private void saveProducts() {
        File archivo = new File("Repository/Products.bin");
        Producto newProduct = new Producto(txtFieldName.getText());
        List<Producto> productos;
        ReadWriteList<Producto> readWrite = new ReadWriteList<>("Products");


        if (txtFieldName.getText().equals("")) {
            txtError.setText("Debes añadir un nombre");
        } else if (archivo.exists()) {

            productos = readWrite.readProducts();
            productos.add(newProduct);

            readWrite.writeProducts(productos);
            txtFieldName.getScene().getWindow().hide(); // 1 

            mainScreen.loadProducts();

        } else {
            productos = new ArrayList<>(List.of(newProduct));
            readWrite.writeProducts(productos);
            txtFieldName.getScene().getWindow().hide();
        }
    }

    @FXML
    private void closeDialog() {
        txtFieldName.getScene().getWindow().hide();
    }
}
