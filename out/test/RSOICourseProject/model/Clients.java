package model;

public class Clients {

    private int id;
    private String name;
    private String surname;
    private String fname;
    private String birthdate;
    private String spp;
    private String npp;
    private String ipp;
    private String whogive;
    private String whengive;
    private String cityborn;
    private String city;
    private String address;
    private String mobilenum;
    private String homenum;
    private String email;
    private String job;
    private String post;
    private String marriage;
    private String citydoc;
    private String nat;
    private String invalid;
    private String old;
    private int money;

    public Clients(int ClientID, String name, String surname, String fname, String birthdate, String spp, String npp, String ipp, String whogive, String whengive, String cityborn, String city, String address, String mobilenum, String homenum, String email, String job, String post, String marriage, String citydoc, String nat, String invalid, String old, int money) {
        this.id = ClientID;
        this.name = name;
        this.surname = surname;
        this.fname = fname;
        this.birthdate = birthdate;
        this.spp = spp;
        this.npp = npp;
        this.ipp = ipp;
        this.whogive = whogive;
        this.whengive = whengive;
        this.cityborn = cityborn;
        this.city = city;
        this.address = address;
        this.mobilenum = mobilenum;
        this.homenum = homenum;
        this.email = email;
        this.job = job;
        this.post = post;
        this.marriage = marriage;
        this.citydoc = citydoc;
        this.nat = nat;
        this.invalid = invalid;
        this.old = old;
        this.money = money;
    }

    public Clients() {
        this.id = 0;
        this.name = "";
        this.surname = "";
        this.fname = "";
        this.birthdate = "";
        this.spp = "";
        this.npp = "";
        this.ipp = "";
        this.whogive = "";
        this.whengive = "";
        this.cityborn = "";
        this.city = "";
        this.address = "";
        this.mobilenum = "";
        this.homenum = "";
        this.email = "";
        this.job = "";
        this.post = "";
        this.marriage = "";
        this.citydoc = "";
        this.nat = "";
        this.invalid = "";
        this.old = "";
        this.money = 0;
    }

    public String getIpp() {
        return ipp;
    }

    public void setIpp(String ipp) {
        this.ipp = ipp;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int ClientID) {
        this.id = ClientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getSpp() {
        return spp;
    }

    public void setSpp(String spp) {
        this.spp = spp;
    }

    public String getNpp() {
        return npp;
    }

    public void setNpp(String npp) {
        this.npp = npp;
    }

    public String getWhogive() {
        return whogive;
    }

    public void setWhogive(String whogive) {
        this.whogive = whogive;
    }

    public String getWhengive() {
        return whengive;
    }

    public void setWhengive(String whengive) {
        this.whengive = whengive;
    }

    public String getCityborn() {
        return cityborn;
    }

    public void setCityborn(String cityborn) {
        this.cityborn = cityborn;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilenum() {
        return mobilenum;
    }

    public void setMobilenum(String mobilenum) {
        this.mobilenum = mobilenum;
    }

    public String getHomenum() {
        return homenum;
    }

    public void setHomenum(String homenum) {
        this.homenum = homenum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getCitydoc() {
        return citydoc;
    }

    public void setCitydoc(String citydoc) {
        this.citydoc = citydoc;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}
