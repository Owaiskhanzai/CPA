package com.example.cpa;

public class cropsmodel {




    private String nameofcrop ;
    private String quantity ;
    private String priceperunit ;
    private  String expirydate ;
    private  String phonenumber ;
    private  String address ;
    private String quality ;
    private  String croptypes;



    private cropsmodel(){}

    private cropsmodel(String nameofcrop, String croptypes, String quantity, String priceperunit, String expirydate, String phonenumber, String address, String quality)
    {

        this.nameofcrop=nameofcrop;
        this.croptypes=croptypes;
        this.quantity=quantity;
        this.priceperunit=priceperunit;
        this.expirydate=expirydate;
        this.phonenumber=phonenumber;
        this.address=address;
        this.quality=quality;


    }



    public String getNameofcrop() {
        return nameofcrop;
    }

    public void setNameofcrop(String nameofcrop) {
        this.nameofcrop = nameofcrop;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPriceperunit() {
        return priceperunit;
    }

    public void setPriceperunit(String priceperunit) {
        this.priceperunit = priceperunit;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getCroptypes() {
        return croptypes;
    }

    public void setCroptypes(String croptypes) {
        this.croptypes = croptypes;
    }
}
