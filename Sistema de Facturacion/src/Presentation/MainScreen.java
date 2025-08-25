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
import Services.ReadWriteList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class MainScreen implements Initializable {

    @FXML
    private ListView<Producto> listAvaiables, listViewFinal;

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

    private void loadProducts() {
        listAvaiables.setItems(FXCollections.observableArrayList(ReadWriteList.readProducts()));
    }

    private void createFolderRepository() {
        File carpeta = new File("src/Repository");
        if (!carpeta.exists()) {
            carpeta.mkdir();
            loadProducts();
        }
        listAvaiables.setItems(FXCollections.observableArrayList());
    }

    @FXML
    private void abrirDialogoPersonalizado() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("addProductScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Añadir producto");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();

        stage.showAndWait();
    }
}
