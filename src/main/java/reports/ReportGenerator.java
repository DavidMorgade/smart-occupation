package reports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class ReportGenerator {
    private static final String DB_URL = "jdbc:sqlite:real_estate.db";

    public static void generateReport() {
        try {
            InputStream reportStream = ReportGenerator.class.getResourceAsStream("/Clientes.jasper");

            Connection connection = DriverManager.getConnection(DB_URL);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ReportTitle", "Lista de Casas y Clientes");

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parameters, connection);

            String pdfFilePath = "reporte_clientes.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFilePath);

            openPDF(pdfFilePath);

            JOptionPane.showMessageDialog(null, "¡Reporte generado exitosamente y abierto!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Método para abrir el archivo PDF
    public static void openPDF(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(file); // Esto abrirá el archivo en el visor predeterminado
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede abrir el archivo PDF en este sistema.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar abrir el archivo PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
