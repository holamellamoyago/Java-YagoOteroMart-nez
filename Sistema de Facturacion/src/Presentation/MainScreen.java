package Presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.Company;
import Model.Producto;
import Services.CreatePDF;
import Services.ReadWriteList;
import Services.Router;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;

public class MainScreen implements Initializable, Router {

    List<Producto> products;

    TextField lasTextField;

    ReadWriteList<Producto> readwriteProducts = new ReadWriteList<>("Products");
    ReadWriteList<Company> readwriteCompanies = new ReadWriteList<>("Companies");

    @FXML
    private TextField txtFieldCuantity;

    @FXML
    private TextField txtFieldPrice;

    @FXML
    private Text txtError;

    @FXML
    private Text txtNameProduct;

    @FXML
    private Button btnCreatePDF;

    @FXML
    private Button btnDeleteProduct;

    @FXML
    private Button btnNewProduct;

    @FXML
    private ListView<Producto> listViewFinal;

    @FXML
    private ListView<Producto> listAvaiables;

    @FXML
    private ChoiceBox<Company> chBoxFirstCompany;

    @FXML
    private ChoiceBox<Company> chBoxSecondCompany;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadProducts();
        loadCompanies();
        // loadPDF();
    }

    @FXML
    private void loadCompanies() {

        chBoxFirstCompany.getItems().addAll(readwriteCompanies.readProducts());
        chBoxSecondCompany.getItems().addAll(readwriteCompanies.readProducts());

        // Selecciona el primer elemento por defecto
        if (!chBoxFirstCompany.getItems().isEmpty()) {
            chBoxFirstCompany.getSelectionModel().select(0);
        }
        if (!chBoxSecondCompany.getItems().isEmpty()) {
            chBoxSecondCompany.getSelectionModel().select(1);
        }

    }

    // private void loadPDF(){
    // chBoxFirstCompany.getSelectionModel().select(0);
    // chBoxSecondCompany.getSelectionModel().select(1);

    // for (int i = 0; i < 1; i++) {
    // Producto pr1 = products.get(i);

    // }
    // }

    public void loadProducts() {
        products = readwriteProducts.readProducts();
        listAvaiables.setItems(FXCollections.observableArrayList());
        listViewFinal.setItems(FXCollections.observableArrayList());

        for (int i = 0; i < products.size(); i++) {
            listAvaiables.getItems().add(products.get(i));
        }

        listAvaiables.setCellFactory(lv -> new ListCell<Producto>() {
            @Override
            protected void updateItem(Producto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getName());
            }
        });

    }

    private void restartTextFields() {
        txtError.setText("");
        txtNameProduct.setText("Selecciona un producto");
        txtFieldCuantity.setText("");
        txtFieldPrice.setText("");

    }

    @FXML
    private void updateTitleCuantity() {
        Integer id = listAvaiables.getSelectionModel().getSelectedIndex();

        if (id > -1) {
            Producto pr = products.get(id);
            if (!txtFieldCuantity.getText().equals(pr.getName())) {
                txtNameProduct.setText(pr.getName());
                txtFieldCuantity.setText(Integer.toString(pr.getCuantity()));
                txtFieldPrice.setText(Double.toString(pr.getPrice()));
            } else if (!txtFieldPrice.getText().equals(Double.toString(pr.getPrice()))) {
                txtFieldPrice.setText(Double.toString(pr.getPrice()));
            } else {
                txtError.setText("Antes de eliminar, selecciona un producto");
            }
        }

    }

    @FXML
    private void deleteProduct() {
        Integer id = listAvaiables.getSelectionModel().getSelectedIndex();

        if (id > -1) {
            readwriteProducts.writeProducts(readwriteProducts.deleteProduct(products.get(id), products));
            loadProducts();
            restartTextFields();
        } else {
            txtError.setText("Antes de eliminar, selecciona un producto");
        }

    }

    @FXML
    private void addFinalProduct() {
        int id = listAvaiables.getSelectionModel().getSelectedIndex();

        try {
            if (id >= 0) {
                Producto pr = products.get(id);
                pr.setCuantity(Integer.parseInt(txtFieldCuantity.getText()));
                pr.setPrice(Double.parseDouble(txtFieldPrice.getText()));
                listViewFinal.getItems().add(pr);

                // TODO HACER X T , loadproducts
                renombrar();

            }
        } catch (Exception e) {
            txtError.setText(e.getMessage());
        }

    }

    private void renombrar() {

        if (listViewFinal.getItems().size() > 0) {

            listViewFinal.setCellFactory(lv -> new ListCell<Producto>() {
                @Override
                protected void updateItem(Producto item, boolean empty) {
                    if (item != null) {
                        String s = item.getName() + ", Cantidad: " + item.getCuantity() + ", " + item.getPrice() + "€";
                        super.updateItem(item, empty);
                        setText(empty || item == null ? "" : s);
                    }
                }
            });
        }

    }

    @FXML
    private void createPDF() {
        Company a = chBoxFirstCompany.selectionModelProperty().get().getSelectedItem();
        Company b = chBoxSecondCompany.selectionModelProperty().get().getSelectedItem();

        CreatePDF.createPDF(listViewFinal.getItems(), new ArrayList<>(List.of(a, b)));
    }

    @FXML
    private void handleLastTextField(MouseEvent e) {
        lasTextField = (TextField) e.getSource();
    }

    @FXML
    private void handleNumpad(ActionEvent e) {
        Button btn = (Button) e.getSource();
        String number = btn.getText();

        if (lasTextField != null) {
            lasTextField.appendText(number);
        }
    }

    @FXML
    private void clearTextfield(ActionEvent e) {
        if (lasTextField != null) {
            lasTextField.setText("");
        } else {
            txtError.setText("Debes seleccionar primero un campo");
        }
    }

    @FXML
    private void appendPoint() {
        if (lasTextField != null) {
            lasTextField.appendText(".");
        } else {
            txtError.setText("Debes seleccionar primero un campo");
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
    private void openDialogCompany() {
        // try {
        // FXMLLoader loader = new
        // FXMLLoader(getClass().getResource("addCompanyScreen.fxml"));
        // Parent root = loader.load();
        // Scene scene = new Scene(root);
        // Stage stage = new Stage();

        // stage.setScene(scene);
        // stage.setTitle("Añadir empresa");

        // stage.show();

        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        // TODO MEJORAR

        openDialog(Router.OwnScreens.CompaniesScreen);
    }

}
