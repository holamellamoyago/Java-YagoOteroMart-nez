package Services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import Model.Company;
import Model.Producto;

public class CreatePDF {
    private static PDDocument documento = new PDDocument();
    private static PDPage page = new PDPage();
    private static PDPageContentStream content;
    private static float pageHeight = page.getMediaBox().getHeight();

    public static void createPDF(List<Producto> products, List<Company> companies) {
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

    private static void initializePDF() {
        try {
            content = new PDPageContentStream(documento, page);
            documento.addPage(page);
            content.setFont(loadFont(), 16);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeProducts(List<Producto> products) {

        int height = 50;


        for (int i = 0; i < products.size(); i++) {

            int cuantity = products.get(i).getCuantity();
            double price = products.get(i).getPrice();
            double total = Double.valueOf(cuantity) * price;

            int width = 50;
            int addedWith = 300;

            try {
                content.beginText();
                content.newLineAtOffset(width, pageHeight - height);
                content.showText(products.get(i).getName());
                content.endText();

                width += addedWith;
                content.beginText();
                content.newLineAtOffset(width, pageHeight - height);
                content.showText(Integer.toString(cuantity));
                content.endText();

                width += 100;
                content.beginText();
                content.newLineAtOffset(width, pageHeight - height);
                content.showText(Double.toString(price));
                content.endText();

                width += 100;
                content.beginText();
                content.newLineAtOffset(width, pageHeight - height);
                content.showText(Double.toString(total));
                content.endText();

                height += 20;

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
