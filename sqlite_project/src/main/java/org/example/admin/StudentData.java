package org.example.admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentData {

    private final StringProperty ID;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final StringProperty DOB;

    public StudentData(String ID, String firstname, String lastname, String email, String dob) {
        this.ID = new SimpleStringProperty(ID);
        this.firstName = new SimpleStringProperty(firstname);
        this.lastName = new SimpleStringProperty(lastname);
        this.email = new SimpleStringProperty(email);
        this.DOB = new SimpleStringProperty(dob);
    }

    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getDOB() {
        return DOB.get();
    }

    public StringProperty DOBProperty() {
        return DOB;
    }

    public void setID (String id) {
        this.setID(id);
    }

    public void setFirstName(String firstName) {
        this.setFirstName(firstName);
    }

    public void setLastName(String lastName){
        this.setLastName(lastName);
    }

    public void setEmail(String email){
        this.setEmail(email);
    }

    public void setDOB(String dob){
        this.setDOB(dob);
    }
}
