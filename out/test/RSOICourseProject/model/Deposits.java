
package model;

public class Deposits {
    private int depositID;
    private String depositName;
    private int minTime;
    private int maxTime;
    private double pBYN;
    private double pUSD;
    private double pEUR; 
    private double pRUB;

    public Deposits(int depositID, String depositName, int minTime, int maxTime, double pBYN, double pUSD, double pEUR, double pRUB) {
        this.depositID = depositID;
        this.depositName = depositName;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.pBYN = pBYN;
        this.pUSD = pUSD;
        this.pEUR = pEUR;
        this.pRUB = pRUB;
    }

        public Deposits() {
        this.depositID = 0;
        this.depositName = "";
        this.minTime = 0;
        this.maxTime = 0;
        this.pBYN = 0;
        this.pUSD = 0;
        this.pEUR = 0;
        this.pRUB = 0;
    }

    public int getDepositID() {
        return depositID;
    }

    public void setDepositID(int depositID) {
        this.depositID = depositID;
    }

    public String getDepositName() {
        return depositName;
    }

    public void setDepositName(String depositName) {
        this.depositName = depositName;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
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
    
    public double getP(Object dID, Object cID){
        return 1;
    }
    
}


