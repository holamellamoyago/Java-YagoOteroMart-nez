package Presentation;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class addCompanyController {
    @FXML
    private TextField txtFieldAdress, txtFieldCity, txtFieldCodPostal, txtFieldCode, txtFieldName, txtFieldPhoneNumber;

    @FXML
    private Text txtError;

    @FXML
    private void saveCompany(){
        if (checkNulls()) {
            
        }
    }

    private boolean checkNulls(){
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
