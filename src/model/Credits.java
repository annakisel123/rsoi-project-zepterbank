
package model;

public class Credits {


    private int creditID;
    private String creditName;
    private double pBYN;
    private double pUSD;
    private double pEUR;
    private double pRUB;

    public Credits() {
        this.creditID = 0;
        this.creditName = "";
        this.pBYN = 0;
        this.pUSD = 0;
        this.pEUR = 0;
        this.pRUB = 0;
    }

    public int getCreditID() {
        return creditID;
    }

    public void setCreditID(int creditID) {
        this.creditID = creditID;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }



    public double getPBYN() {
        return pBYN;
    }

    public void setPBYN(double pBYN) {
        this.pBYN = pBYN;
    }

    public double getPUSD() {
        return pUSD;
    }

    public void setPUSD(double pUSD) {
        this.pUSD = pUSD;
    }

    public double getPEUR() {
        return pEUR;
    }

    public void setPEUR(double pEUR) {
        this.pEUR = pEUR;
    }

    public double getPRUB() {
        return pRUB;
    }

    public void setPRUB(double pRUB) {
        this.pRUB = pRUB;
    }

}
