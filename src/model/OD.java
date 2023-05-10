package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author 
 */
public class OD {


    private int dogId;
    private int clientId;
    private float summa;
    private float percent;
    private Date dateOfStart;
    private Date dateOfFinish;
    private long period;
    private int curId;
    private int depId;
    private String curName;
    private String depName;
    private float accumulation;

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public int getDogId() {
        return dogId;
    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public float getSumma() {
        return summa;
    }

    public void setSumma(float summa) {
        this.summa = summa;
    }

    public void setSumma(float summa, String cur) {
        float kurs = 1;
        if (cur.equals("USD")) {
            kurs = (float) 2.026;
        }
        if (cur.equals("EUR")) {
            kurs = (float) 2.484;
        }
        if (cur.equals("RUB")) {
            kurs = (float) 0.0324;
        }
        this.summa = summa * kurs;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public void setPercent(int depID, int curID) {

        double[][] percent = {{2.6, 0.2, 0.2, 0.2}, {9.2, 1.0, 8.0, 1.5}, {9.8, 1.5, 8.5, 2.2}};
        float a = (float) percent[depID - 1][curID - 1];
        this.percent = a;
    }

    public int getCurId() {
        return curId;
    }

    public void setCurId(int curId) {
        this.curId = curId;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public String getDateOfStart() {

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String s = df.format(dateOfStart);
        return s;
    }

    public String getDateOfStartS() {

        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        String s = df.format(dateOfStart);
        return s;
    }

    public void setDateOfStart(String dateOfStart) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date startDate;
        startDate = df.parse(dateOfStart);
        this.dateOfStart = startDate;
    }

    public void setDateOfStartS(String dateOfStart) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        startDate = df.parse(dateOfStart);
        this.dateOfStart = startDate;
    }

    public String getDateOfFinish() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String s = df.format(dateOfFinish);
        return s;
    }

    public String getDateOfFinishS() {
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        String s = df.format(dateOfFinish);
        return s;
    }

    public void setDateOfFinish(String dateOfStart, long period) throws ParseException {

        long s;
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date startDate;
        startDate = df.parse(dateOfStart);
        s = startDate.getTime();
        System.out.println(s);
        s = s + period * 30 * 24 * 60 * 60 * 1000;
        System.out.println(s);
        Date f = new Date(s);
        this.dateOfFinish = f;
        System.out.println("dd" + f);

    }

    public void setDateOfFinish(String dateOfFinish) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date startDate;
        startDate = df.parse(dateOfFinish);
        this.dateOfFinish = startDate;
    }

    public void setDateOfFinishS(String dateOfFinish) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        startDate = df.parse(dateOfFinish);
        this.dateOfFinish = startDate;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public float getAccumulation() {
        return accumulation;
    }

    public void setAccumulation(float accumulation, String cur) {
        float kurs = 1;
        if (cur.equals("USD")) {
            kurs = (float) 2.026;
        }
        if (cur.equals("EUR")) {
            kurs = (float) 2.484;
        }
        if (cur.equals("RUB")) {
            kurs = (float) 0.0324;
        }
        this.accumulation = accumulation * kurs;
    }

    public void setAccumulation(int period, float percent, float summa) {
        this.accumulation = (((float) period / 12) * percent * summa / 100);
    }

    public void setAccumulation(float accumulation) {
        this.accumulation = accumulation;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

}
