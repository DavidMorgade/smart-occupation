package mocks;

import java.util.Date;

public class House {
    Client client;
    int expNumber;
    String location;
    int meters;
    int rooms;
    int bathrooms;
    int mensualPrice;
    Date entryDate;
    Date exitDate;
    int houseId;

    public House(Client client, int expNumber, Date entryDate, Date exitDate, int houseId, String location, int meters, int rooms, int bathrooms, int mensualPrice) {
        this.client = client;
        this.expNumber = expNumber;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.houseId = houseId;
        this.location = location;
        this.meters = meters;
        this.rooms = rooms;
        this.bathrooms = bathrooms;
        this.mensualPrice = mensualPrice;
    }

    public Client getClient() {
        return client;
    }

    public String getLocation() {
        return location;
    }

    public int getMeters() {
        return meters;
    }

    public int getRooms() {
        return rooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public int getMensualPrice() {
        return mensualPrice;
    }

    public int getHouseId() {
        return houseId;
    }

    public int getExpNumber() {
        return expNumber;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public Date getExitDate() {
        return exitDate;
    }

}
