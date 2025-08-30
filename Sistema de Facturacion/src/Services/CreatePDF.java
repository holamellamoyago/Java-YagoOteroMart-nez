package Services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class CreatePDF {
    private static PDDocument documento = new PDDocument();
    private static PDPage page = new PDPage();
    private static PDPageContentStream content;
    private static float pageHeight = page.getMediaBox().getHeight();
    
    



    public static void createPDF(List<String> products){
        initializePDF();

        writeProducts(products);

        try {
            content.close();
            documento.save("Factura.pdf");
            System.out.println("Documento PDF Creado");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private static void initializePDF(){
        try {
            content = new PDPageContentStream(documento, page);
            documento.addPage(page);
            content.setFont(loadFont(), 16);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeProducts(List<String> products){

        int height = 50;
        for (int i = 0; i < products.size(); i++) {
            try {
                content.beginText();

                content.newLineAtOffset(10, pageHeight-height);
                content.showText(products.get(i).toString());
                height += 20;

                content.endText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        
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
