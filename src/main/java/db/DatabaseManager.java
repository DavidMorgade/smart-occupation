package db;

import mocks.Client;
import mocks.House;

import java.sql.*;
import java.util.Date;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:real_estate.db";

    // Conexión a la base de datos
    public static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }

    // Inicializar la base de datos
    public static void initializeDatabase() {
        String createClientsTable = """
                    CREATE TABLE IF NOT EXISTS Clients (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        house_id INTEGER,
                        full_name TEXT NOT NULL,
                        email TEXT NOT NULL,
                        phone TEXT,
                        dni TEXT NOT NULL UNIQUE,
                        card_number INTEGER NOT NULL UNIQUE,
                        FOREIGN KEY (house_id) REFERENCES Houses(id)
                    );
                """;

        String createHousesTable = """
                    CREATE TABLE IF NOT EXISTS Houses (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        client_id INTEGER NOT NULL,
                        exp_number INTEGER NOT NULL,
                        entry_date TEXT NOT NULL,
                        exit_date TEXT NOT NULL,
                        house_id INTEGER UNIQUE NOT NULL,
                        location TEXT NOT NULL,
                        meters INTEGER NOT NULL,
                        rooms INTEGER NOT NULL,
                        bathrooms INTEGER NOT NULL,
                        mensual_price INTEGER NOT NULL,
                        FOREIGN KEY (client_id) REFERENCES Clients(id)
                    );
                """;

        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(createClientsTable);
            statement.execute(createHousesTable);
        } catch (SQLException e) {
            System.out.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }

    public static void deleteAllTables() {
        String deleteClientsTable = "DROP TABLE IF EXISTS Clients;";
        String deleteHousesTable = "DROP TABLE IF EXISTS Houses;";
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(deleteClientsTable);
            statement.execute(deleteHousesTable);
        } catch (SQLException e) {
            System.out.println("Error al eliminar las tablas: " + e.getMessage());
        }
    }

    // Insertar cliente
    public static void addClient(String fullName, String email, String phone, String dni, int cardNumber, int id, int houseId) {
        String insertSQL = """
                    INSERT INTO Clients (full_name, email, phone, dni, card_number, id, house_id)
                    VALUES (?, ?, ?, ?, ?, ?, ?);
                """;

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(insertSQL)) {
            statement.setString(1, fullName);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setString(4, dni);
            statement.setInt(5, cardNumber);
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar el cliente: " + e.getMessage());
        }
    }

    // Insertar vivienda
    public static void addHouse(int clientId, int expNumber, Date entryDate, Date exitDate, int houseId, String location, int meters, int rooms, int bathrooms, int mensualPrice) {
        String insertSQL = """
                    INSERT INTO Houses (client_id, exp_number, entry_date, exit_date, house_id, location, meters, rooms, bathrooms, mensual_price)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(insertSQL)) {
            statement.setInt(1, clientId);
            statement.setInt(2, expNumber);
            statement.setString(3, new java.text.SimpleDateFormat("yyyy-MM-dd").format(entryDate));
            statement.setString(4, new java.text.SimpleDateFormat("yyyy-MM-dd").format(exitDate));
            statement.setInt(5, houseId);
            statement.setString(6, location);
            statement.setInt(7, meters);
            statement.setInt(8, rooms);
            statement.setInt(9, bathrooms);
            statement.setInt(10, mensualPrice);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar la vivienda: " + e.getMessage());
        }
    }

    // Consultar clientes
    public static ResultSet getAllClients() {
        String querySQL = "SELECT * FROM Clients;";
        try {
            Connection connection = connect();
            return connection.prepareStatement(querySQL).executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al consultar clientes: " + e.getMessage());
            return null;
        }
    }

    // Consultar viviendas
    public static ResultSet getAllHouses() {
        String querySQL = "SELECT * FROM Houses;";
        try {
            Connection connection = connect();
            return connection.prepareStatement(querySQL).executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al consultar viviendas: " + e.getMessage());
            return null;
        }
    }

    // Vivienda asociada a un cliente
    public static House getHouseFromClient(int clientId) {
        String querySQL = """
                    SELECT * FROM Houses
                    WHERE client_id = ?;
                """;

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(querySQL)) {
            statement.setInt(1, clientId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new House(
                        null,
                        result.getInt("exp_number"),
                        new Date(),
                        new Date(),
                        result.getInt("id"),
                        result.getString("location"),
                        result.getInt("meters"),
                        result.getInt("rooms"),
                        result.getInt("bathrooms"),
                        result.getInt("mensual_price")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la vivienda del cliente: " + e.getMessage());
        }
        return null;
    }

    // Cliente asociado a una vivienda
    public static Client getClientFromHouse(int houseId) {
        String querySQL = """
                    SELECT * FROM Clients
                    WHERE id = ?;
                """;

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(querySQL)) {
            statement.setInt(1, houseId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Client(
                        result.getString("full_name"),
                        result.getString("email"),
                        result.getString("phone"),
                        result.getString("dni"),
                        result.getInt("card_number"),
                        result.getInt("id"),
                        houseId
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el cliente de la vivienda: " + e.getMessage());
        }
        return null;
    }
}

