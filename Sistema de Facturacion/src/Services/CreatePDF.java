package Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.DateFormatter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import Model.Company;
import Model.Producto;
import javafx.util.converter.LocalDateStringConverter;

public class CreatePDF {
    private static PDDocument documento = new PDDocument();
    private static PDPage page = new PDPage();
    private static PDPageContentStream content;
    private static float pageHeight = page.getMediaBox().getHeight();
    private static float pageWidth = page.getMediaBox().getWidth();

    private static final int heightHeader = 50;
    private static final int heightCompany = 200;
    private static final int heightProducts = 400;

    public static void createPDF(List<Producto> products, List<Company> companies) {
        initializePDF();

        writeProducts(products);

        writeCompanies(companies);

        writeHeader();

        try {
            content.close();
            documento.save("Factura.pdf");
            System.out.println("Documento PDF Creado");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeHeader() {
        writeDate();

        writeNumberOfBill();

    }

    private static void writeNumberOfBill() {
        File totalFile = new File("Repository/TotalNumber.txt");

        try {
            Scanner sc = new Scanner(totalFile);
            String s = sc.nextLine();

            updateNumberBill(s, totalFile);

            content.beginText();
            content.newLineAtOffset(50, pageHeight - heightHeader - 30);
            content.showText("FACTURA");
            content.endText();

            content.beginText();
            content.newLineAtOffset(50, pageHeight - heightHeader - 50);
            content.showText("Nº: " + s);
            content.endText();


            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String updateNumberBill(String s, File totalFile) {
        int n = Integer.valueOf(s) + 1;
        s = Integer.toString(n);

        try {
            FileWriter out;
            out = new FileWriter(totalFile);
            out.write(s);
            out.close();

            return s;
        } catch (IOException e) {
            e.printStackTrace();
            return "0";
        }
    }

    private static void writeDate() {

        try {
            content.beginText();
            content.newLineAtOffset(50, pageHeight - heightHeader);

            String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            content.showText(fecha);
            content.endText();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void writeDetailscompany(String t, int height, boolean rightAlign) {

        float x;

        try {

            if (rightAlign) {
                float textWidth = loadFont().getStringWidth(t) / 1000 * 12;
                x = pageWidth - textWidth - 50;

            } else {
                x = 50;
            }

            content.beginText();
            content.newLineAtOffset(x, pageHeight - height);
            content.showText(t);
            content.endText();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void writeCompany(Company company, boolean rightAlign) {

        int height = heightCompany;

        try {

            writeDetailscompany("DATOS DEL CLIENTE", height, rightAlign);

            height += 30;

            writeDetailscompany(company.getName(), height, rightAlign);

            height += 25;

            writeDetailscompany(company.getCompanyCode(), height, rightAlign);

            height += 25;

            writeDetailscompany(company.getAdress(), height, rightAlign);

            height += 25;

            writeDetailscompany(company.getPostal() + ", " + company.getCity(), height, rightAlign);

            height += 25;

            writeDetailscompany(company.getPhoneNumber(), height, rightAlign);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeCompanies(List<Company> companies) {

        final Company firstCompany = companies.get(0);
        final Company secondCompany = companies.get(1);

        int height = 50;

        writeCompany(firstCompany, false);

        drawVerticalLine(pageHeight - heightCompany + 20, pageHeight - heightCompany - 150);

        writeCompany(secondCompany, true);

    }

    private static void drawVerticalLine(float height, float finalHeight) {
        try {

            float mitad = pageWidth / 2;

            content.setStrokingColor(0, 0, 0);
            content.moveTo(mitad, height); // Punto inicial (x=50, y=alto-40)
            content.lineTo(mitad, finalHeight); // Punto final (x=500, y=alto-40)
            content.stroke(); // Dibuja la línea

        } catch (IOException e) {
            e.printStackTrace();
        } // Color negro
    }

    private static void initializePDF() {
        try {
            content = new PDPageContentStream(documento, page);
            documento.addPage(page);
            content.setFont(loadFont(), 12);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeProducts(List<Producto> products) {

        int height = heightProducts;
        final int WIDTHNAME = 50;
        final int WIDTHCUANTITY = 335;
        final int WIDTHPRICE = 435;
        final int WIDTHTOTAL = 535;

        // Escribo "detalle, cantidad, precio, total "

        try {
            content.beginText();
            content.newLineAtOffset(WIDTHNAME, pageHeight - height);
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

            height += 10;

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            content.setStrokingColor(0, 0, 0);
            content.moveTo(50, pageHeight - height); // Punto inicial (x=50, y=alto-40)
            content.lineTo(pageWidth - 50, pageHeight - height); // Punto final (x=500, y=alto-40)
            content.stroke(); // Dibuja la línea
        } catch (IOException e) {
            e.printStackTrace();
        } // Color negro

        height += 20;

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
