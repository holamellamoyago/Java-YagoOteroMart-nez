package Presentation;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Model.Producto;
import Services.CreatePDF;
import Services.ReadWriteList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MainScreen implements Initializable {

    List<Producto> products;
    
    @FXML
    TextField lasTextField; 

    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnCreatePDF;

    @FXML
    private Button btnAddProduct;

    @FXML
    private Button btnDeleteProduct;

    @FXML
    private Button btnNewProduct;

    @FXML
    private Button btnSubtract;

    @FXML
    private ListView<String> listViewFinal;

    @FXML
    private ListView<String> listAvaiables;

    @FXML
    private Text txtCuantityProduct;

    @FXML
    private Text txtNameProduct;

    @FXML
    private Text txtError;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadProducts();
        
    }

    public void loadProducts() {
        products = ReadWriteList.readProducts();
        listAvaiables.setItems(FXCollections.observableArrayList());
        listViewFinal.setItems(FXCollections.observableArrayList());

        for (int i = 0; i < products.size(); i++) {
            listAvaiables.getItems().add(products.get(i).getName());
        }

    }

    private void restartTexts() {
        txtError.setText("");
        txtNameProduct.setText("Selecciona un producto");
        txtCuantityProduct.setText("");

    }

    private void turnOnButtons() {

        btnAdd.setDisable(false);
        btnSubtract.setDisable(false);
        btnAddProduct.setDisable(false);

    }

    @FXML
    private void updateTitleCuantity() {
        Integer id = listAvaiables.getSelectionModel().getSelectedIndex();

        if (id != null) {
            Producto pr = products.get(id);
            if (!pr.getName().equals(txtNameProduct.getText())) {
                txtNameProduct.setText(pr.getName());
                Integer n = pr.getCuantity();
                txtCuantityProduct.setText(n.toString());
                turnOnButtons();

            } else if (!txtCuantityProduct.getText().equals(Integer.toString(pr.getCuantity()))) {
                txtCuantityProduct.setText(Integer.toString(products.get(id).getCuantity()));
            }
        }

    }

    @FXML
    private void abrirDialogoPersonalizado() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addProductScreen.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        addProductScreen controller = loader.getController();
        controller.setMainScreen(this);

        stage.setTitle("Añadir producto");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();

        stage.showAndWait();
    }

    @FXML
    private void deleteProduct() {
        Integer id = listAvaiables.getSelectionModel().getSelectedIndex();

        if (id >= 0) {
            ReadWriteList.deleteProduct(products.get(id));
            loadProducts();
            restartTexts();
            turnOnButtons();
        } else {
            txtError.setText("Antes de eliminar, selecciona un producto");
        }
    }

    @FXML
    private void addCuantity() {
        int id = listAvaiables.getSelectionModel().getSelectedIndex();
        Producto pr = products.get(id);
        pr.setCuantity(pr.getCuantity() + 1);

        updateTitleCuantity();
    }

    @FXML
    private void substractCuantity() {
        int id = listAvaiables.getSelectionModel().getSelectedIndex();
        Producto pr = products.get(id);

        if (pr.getCuantity() > 1) {
            pr.setCuantity(pr.getCuantity() - 1);
            updateTitleCuantity();
        }

    }

    @FXML
    private void addFinalProduct() {
        int id = listAvaiables.getSelectionModel().getSelectedIndex();
        Producto pr = products.get(id);

        listViewFinal.getItems().add(pr.toString());

    }

    @FXML
    private void createPDF(){
        CreatePDF.createPDF(listViewFinal.getItems());
    }
    
    @FXML
    private void handleLastTextField(MouseEvent e){
        lasTextField = (TextField) e.getSource();
    }

    @FXML
    private void handleNumpad(ActionEvent e){
        Button btn = (Button) e.getSource();
        String number = btn.getText();
        if (lasTextField != null) {
            lasTextField.setText(number);
        }
        
    }

}
