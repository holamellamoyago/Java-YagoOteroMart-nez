import java.io.File;

import org.apache.pdfbox.jbig2.decoder.arithmetic.ArithmeticDecoder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    static PDDocument documento = new PDDocument();
    static PDPage pagina = new PDPage();
    PDFont fuente = loadFont();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Presentation/MainScreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("O Forno de Juan - Facturas");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public static void main(String[] args) throws Exception {
        launch(args);
        PDPageContentStream contenido = new PDPageContentStream(documento, pagina);

        contenido.close();
    }

    private static PDFont loadFont() {
        try {
            File archivoFuente = new File("lib/Roboto-Variable.ttf");
            PDFont fuente = PDType0Font.load(documento, archivoFuente);

            return fuente;
        } catch (Exception e) {
            throw new ArithmeticException("Error loading new fonts");
        }
    }

}
