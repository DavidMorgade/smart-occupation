import mocks.Client;
import mocks.House;
import org.junit.jupiter.api.*;
import db.DatabaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DatabaseManagerTest {

    @BeforeAll
    void setup() {
        // Use in-memory database for testing
        DatabaseManager.deleteAllTables();
        DatabaseManager.initializeDatabase();
    }

    @AfterAll
    void teardown() {
        DatabaseManager.deleteAllTables();
    }

    @Test
    void testAddClient() {
        DatabaseManager.addClient("John Doe", "john.doe@example.com", "123456789", "12345678A", 111122223, 1, 1);
        try (ResultSet clients = DatabaseManager.getAllClients()) {
            assertTrue(clients.next(), "Client should exist in database");
            assertEquals("John Doe", clients.getString("full_name"));
            assertEquals("john.doe@example.com", clients.getString("email"));
        } catch (Exception e) {
            fail("Error fetching clients: " + e.getMessage());
        }
    }

    @Test
    void testAddHouse() {
        DatabaseManager.addHouse(1, 1001, new Date(), new Date(), 1, "Main Street 123", 120, 3, 2, 1500);
        try (ResultSet houses = DatabaseManager.getAllHouses()) {
            assertTrue(houses.next(), "House should exist in database");
            assertEquals("Main Street 123", houses.getString("location"));
            assertEquals(120, houses.getInt("meters"));
        } catch (Exception e) {
            fail("Error fetching houses: " + e.getMessage());
        }
    }

    @Test
    void testGetHouseFromClient() {
        House house = DatabaseManager.getHouseFromClient(1);
        assertNotNull(house, "House should be retrieved for client");
        assertEquals("Main Street 123", house.getLocation());
    }


    @Test
    void testDeleteTables() {
        DatabaseManager.deleteAllTables();
        try (ResultSet clients = DatabaseManager.getAllClients()) {
            assertFalse(clients.next(), "Clients table should be empty");
        } catch (Exception e) {
            System.out.println("Error fetching clients: " + e.getMessage());
        }
        try (ResultSet houses = DatabaseManager.getAllHouses()) {
            assertFalse(houses.next(), "Houses table should be empty");
        } catch (Exception e) {
            System.out.println("Error fetching houses: " + e.getMessage());
        }
    }
}
