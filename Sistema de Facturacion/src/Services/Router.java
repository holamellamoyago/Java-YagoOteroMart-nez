package Services;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public interface Router {

    public enum OwnScreens {
        CompaniesScreen, addCompanyScreen
    };

    public default void openDialog(OwnScreens screen) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(screen+".fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("AAAAAA");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}