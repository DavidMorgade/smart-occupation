package mocks;

public class Client {

    String fullName;
    String email;
    String phone;
    String dni;
    int cardNumber;
    int id;
    int houseID;


    public Client(String fullName, String email, String phone, String dni, int cardNumber, int clientID, int houseID) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.dni = dni;
        this.cardNumber = cardNumber;
        this.id = clientID;
        this.houseID = houseID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }


    public String getDni() {
        return dni;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getHouseID() {
        return houseID;
    }

    public int getID() {
        return id;
    }
}
