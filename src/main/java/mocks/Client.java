package mocks;

public class Client {

    String fullName;
    String email;
    String phone;
    String dni;
    int cardNumber;
    int id;


    public Client(String fullName, String email, String phone, String dni, int cardNumber, int clientID) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.dni = dni;
        this.cardNumber = cardNumber;
        this.id = clientID;
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

    public int getID() {
        return id;
    }
}
