import components.*;
import db.DatabaseManager;
import mocks.House;
import pages.LoginPage;

import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Date;

public class SmartOccupation {


    public static void main(String[] args) {
        // inicializamos la DB
        DatabaseManager.initializeDatabase();
        // Agregar varios clientes usando un loop
        for (int i = 1; i <= 10; i++) {
            String fullName = "Cliente " + i;
            String email = "cliente" + i + "@example.com";
            String phone = "12345678" + i;
            String dni = "DNI" + i;
            int cardNumber = 41111111 + i;
            DatabaseManager.addClient(fullName, email, phone, dni, cardNumber);
        }

        // Agregar varias viviendas asociadas a los clientes
        for (int i = 1; i <= 10; i++) {
            int clientId = i; // Asociar cada vivienda al cliente con el mismo ID
            int expNumber = 1000 + i;
            Date entryDate = new Date();
            Date exitDate = new Date(entryDate.getTime() + (7L * 24 * 60 * 60 * 1000)); // Una semana después
            int houseId = 2000 + i;
            String location = "Calle " + i + " de Ejemplo";
            int meters = 100 + i * 10;
            int rooms = 2 + i % 3; // Alternar entre 2, 3, 4 habitaciones
            int bathrooms = 1 + i % 2; // Alternar entre 1 y 2 baños
            int mensualPrice = 500 + i * 50;
            DatabaseManager.addHouse(clientId, expNumber, entryDate, exitDate, houseId, location, meters, rooms, bathrooms, mensualPrice);
        }
        // Leer viviendas y mostrarlas
        System.out.println("\nViviendas en la base de datos:");
        try (ResultSet houses = DatabaseManager.getAllHouses()) {
            while (houses != null && houses.next()) {
                House house = new House(
                        null, // El cliente puede ser resuelto con un JOIN
                        houses.getInt("exp_number"),
                        new java.util.Date(), // Convertir fechas correctamente
                        new java.util.Date(),
                        houses.getInt("house_id"),
                        houses.getString("location"),
                        houses.getInt("meters"),
                        houses.getInt("rooms"),
                        houses.getInt("bathrooms"),
                        houses.getInt("mensual_price")
                );
                System.out.println("Ubicación: " + house.getLocation() + ", Precio Mensual: $" + house.getMensualPrice());
            }
        } catch (SQLException e) {
            System.out.println("Error al leer viviendas: " + e.getMessage());
        }

        // Inicializamos la Interfaz
        SwingUtilities.invokeLater(SmartOccupation::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        boolean isLogged = false;
        JFrame frame = new JFrame("Smart Occupation");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);

        // Main layout container
        MainPanel mainPanel = new MainPanel();
        Header header = new Header(mainPanel);

        LoginPage loginPage = new LoginPage(mainPanel, header, isLogged);
        if (!isLogged) {
            header.setVisible(false);
        }
        frame.add(header, BorderLayout.NORTH);
        frame.add(mainPanel);


        mainPanel.add(loginPage, BorderLayout.CENTER);

        frame.setVisible(true);


    }

}
