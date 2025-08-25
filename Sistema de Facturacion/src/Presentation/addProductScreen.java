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

public class addProductScreen {
    @FXML
    private Button btnAddPrroduct;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtFieldName;

    @FXML
    private Text txtError;

    @FXML
    private void saveProducts() {
        File archivo = new File("Repository/Products.bin");
        Producto newProduct = new Producto(txtFieldName.getText());
        List<Producto> productos;

        if (txtFieldName.getText().equals("")) {
            txtError.setText("Debes añadir un nombre");
        } else if (archivo.exists()) {

            productos = ReadWriteList.readProducts();
            productos.add(newProduct);

            ReadWriteList.writeProducts(productos);
            txtFieldName.getScene().getWindow().hide();

        } else {
            productos = new ArrayList<>(List.of(newProduct));
            ReadWriteList.writeProducts(productos);
            txtFieldName.getScene().getWindow().hide();
        }
    }
}
