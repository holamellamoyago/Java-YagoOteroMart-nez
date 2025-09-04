package Presentation;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.Company;
import Services.ReadWriteList;
import Services.Router;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class CompaniesController implements Initializable, Router {

    @FXML
    private ListView<Company> listCompanies;

    ReadWriteList<Company> readWriteCompany = new ReadWriteList<>("Companies");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Company> companies = readWriteCompany.readProducts();
        listCompanies.getItems().setAll(companies);
    }

    @FXML
    private void addCompany() {
        openDialog(Router.OwnScreens.addCompanyScreen);

        listCompanies.getItems().setAll(readWriteCompany.readProducts());
    }

    @FXML
    private void deleteCompany() {
        int id = listCompanies.getSelectionModel().getSelectedIndex();

        if (id > -1) {

            List<Company> newCompanies = new ArrayList<>(listCompanies.getItems());
            newCompanies.remove(id);

            readWriteCompany.writeProducts(newCompanies);
            
            listCompanies.getItems().setAll(newCompanies);
            

        }
    }
}
