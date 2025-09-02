package Presentation;

import java.io.Serializable;
import java.util.List;

import Model.Company;
import Services.ReadWriteList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class addCompanyController {

    ReadWriteList<Company> readWrite = new ReadWriteList<>("Companies");

    @FXML
    private TextField txtFieldAdress, txtFieldCity, txtFieldCodPostal, txtFieldCode, txtFieldName, txtFieldPhoneNumber;

    @FXML
    private Text txtError;

    @FXML
    private void saveCompany(){


        if (checkNulls()) {

            Company company = new Company(txtFieldAdress.getText(), txtFieldCity.getText(),
             txtFieldCode.getText(), txtFieldName.getText(), txtFieldCodPostal.getText(), txtFieldPhoneNumber.getText());

            List<Company> companies = readWrite.readProducts();
            companies.add(company);

            readWrite.writeProducts(companies);

            txtError.getScene().getWindow().hide();

            
        }
    }

    private boolean checkNulls() {
        boolean camposCompletados = true;

        if (txtFieldAdress.getText().equals("")) {
            txtError.setText("Debes añadir una dirección");
            camposCompletados = false;
        }

        if (txtFieldCity.getText().equals("")) {
            txtError.setText("Debes añadir una ciudad");
            camposCompletados = false;
        }

        if (txtFieldCodPostal.getText().equals("")) {
            txtError.setText("Debes añadir un código postal");
            camposCompletados = false;
        }

        if (txtFieldCode.getText().equals("")) {
            txtError.setText("Debes añadir un número de empresa");
            camposCompletados = false;
        }

        if (txtFieldName.getText().equals("")) {
            txtError.setText("Debes añadir un nombre");
            camposCompletados = false;
        }

        if (txtFieldPhoneNumber.getText().equals("")) {
            txtError.setText("Debes añadir un número de teléfono");
            camposCompletados = false;
        }

        return camposCompletados;
    }
}
