
package model;

public class Invoices {
    
    private int ivID;
    private String name;
    private String number;
    private String type;
    private String kod;
    private float credit;
    private float debit;
    private float saldo;
    private int clientID;
    private int dogID;

    public Invoices() {
        this.ivID = 0;
        this.name = "";
        this.number = "";
        this.type = "";
        this.kod = "";
        this.credit = 0;
        this.debit = 0;
        this.saldo = 0;
        this.clientID = 0;
        this.dogID = 0;
    }

    public int getDogID() {
        return dogID;
    }

    public void setDogID(int dogID) {
        this.dogID = dogID;
    }

    public int getIvID() {
        return ivID;
    }

    public void setIvID(int ivID) {
        this.ivID = ivID;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public float getDebit() {
        return debit;
    }

    public void setDebit(float debit) {
        this.debit = debit;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
    
}
