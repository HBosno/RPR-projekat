package ba.unsa.etf.rpr.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * JavaBean class modelling a profile containing data for different users
 *
 * @author Hamza Bosno
 */

public class Profile implements Idable, Serializable{

    private int id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String adress;
    private String telephoneNumber;

    public Profile(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAdress() {
        return adress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name=" + name +
                ", surname=" + surname +
                ", email=" + email +
                ", adress=" + adress +
                ", telephone number=" + telephoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return id == profile.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, password, email, adress, telephoneNumber);
    }
}
