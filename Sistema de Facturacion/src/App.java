import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.jbig2.decoder.arithmetic.ArithmeticDecoder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import Model.Producto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {


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
        // PDPageContentStream contenido = new PDPageContentStream(documento, pagina);

        // contenido.close();

        
        // Producto pr1 = new Producto("Corasanes");
        // List<Producto> productos = new ArrayList<>(List.of(pr1));

        // String urlCarpeta = "src/Repository";
        // File carpeta = new File(urlCarpeta);

        // if (!carpeta.exists()) {
        //     carpeta.mkdir();
        // }

        // String urlArchivo = urlCarpeta + "/Productos.bin";

        // ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(urlArchivo));
        // out.writeObject(productos);
        // out.close();
        
        
        // ObjectInputStream in = new ObjectInputStream(new FileInputStream(urlArchivo));
        // System.out.println(in.readObject());
        // in.close();

        

    }


}
