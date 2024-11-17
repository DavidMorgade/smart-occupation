package mocks;

public class Client {

    String fullName;
    String email;
    String phone;
    String dni;
    int cardNumber;


    public Client(String fullName, String email, String phone, String dni, int cardNumber) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.dni = dni;
        this.cardNumber = cardNumber;
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
}
