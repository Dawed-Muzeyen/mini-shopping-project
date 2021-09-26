package edu.miu.cs.se.paymentsubsystem.model;


import javax.persistence.*;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue
    private long id;
    private String nameOnCard;
    private String expirationDate;
    private String cardNum;
    private String cardType;


    public CreditCard(String nameOnCard, String expirationDate, String cardNum, String cardType) {
        this.nameOnCard = nameOnCard;
        this.expirationDate = expirationDate;
        this.cardNum = cardNum;
        this.cardType = cardType;

    }
    public CreditCard() {
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }







    public String getNameOnCard() {
        return nameOnCard;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getCardType() {
        return cardType;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "nameOnCard='" + nameOnCard + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", cardType='" + cardType + '\'' +

                '}';
    }
}
