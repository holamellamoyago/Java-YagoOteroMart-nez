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
    private static float pageWidth = page.getMediaBox().getWidth();

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


    private static void writeCompany(){

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

        int height = 30;
        final int WIDTHNAME = 50;
        final int WIDTHCUANTITY = 335;
        final int WIDTHPRICE = 435;
        final int WIDTHTOTAL = 535;

        // Escribo "detalle, cantidad, precio, total "

        try {
            content.beginText();
            content.newLineAtOffset(WIDTHNAME , pageHeight - height);
            content.showText("DETALLE");
            content.endText();

            content.beginText();
            content.newLineAtOffset(WIDTHCUANTITY - 25, pageHeight - height);
            content.showText("CANTIDAD");
            content.endText();

            content.beginText();
            content.newLineAtOffset(WIDTHPRICE - 25, pageHeight - height);
            content.showText("PRECIO");
            content.endText();

            content.beginText();
            content.newLineAtOffset(WIDTHTOTAL - 25, pageHeight - height);
            content.showText("TOTAL");
            content.endText();

            height += 25;

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            content.setStrokingColor(0, 0, 0);
            content.moveTo(50, pageHeight - 40); // Punto inicial (x=50, y=alto-40)
            content.lineTo(pageWidth-50, pageHeight - 40); // Punto final (x=500, y=alto-40)
            content.stroke(); // Dibuja la línea
        } catch (IOException e) {
            e.printStackTrace();
        } // Color negro

        height+= 10;

        for (int i = 0; i < products.size(); i++) {

            int cuantity = products.get(i).getCuantity();
            double price = products.get(i).getPrice();
            double total = Double.valueOf(cuantity) * price;

            // int addedWith = 300;

            try {
                content.beginText();
                content.newLineAtOffset(WIDTHNAME, pageHeight - height);
                content.showText(products.get(i).getName());
                content.endText();

                content.beginText();
                content.newLineAtOffset(WIDTHCUANTITY, pageHeight - height);
                content.showText(Integer.toString(cuantity));
                content.endText();

                content.beginText();
                content.newLineAtOffset(WIDTHPRICE, pageHeight - height);
                content.showText(Double.toString(price));
                content.endText();

                content.beginText();
                content.newLineAtOffset(WIDTHTOTAL, pageHeight - height);
                content.showText(Double.toString(total));
                content.endText();

                height += 25;

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
