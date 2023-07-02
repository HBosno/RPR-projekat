package ba.unsa.etf.rpr.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * JavaBean class for modelling a list of registered cards for a user
 *
 * @author Hamza Bosno
 */

public class Card implements Idable, Serializable{

    private int id;
    private int serialNumber;
    private CardType cardType;
    private double balance;
    private boolean monthlyCoupon;
    private Profile profile;

    public Card(){}

    public Card(int id, int serialNumber, CardType cardType, double balance, boolean monthlyCoupon, Profile profile) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.cardType = cardType;
        this.balance = balance;
        this.monthlyCoupon = monthlyCoupon;
        this.profile = profile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isMonthlyCoupon() {
        return monthlyCoupon;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setMonthlyCoupon(boolean monthlyCoupon) {
        this.monthlyCoupon = monthlyCoupon;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", serial number=" + serialNumber +
                ", card type=" + cardType +
                ", balance=" + balance +
                ", monthly coupon=" + monthlyCoupon +
                ", profile =" + profile +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serialNumber, cardType, balance, monthlyCoupon, profile);
    }
}
