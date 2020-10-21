package com.example.sirsitalaxmienterprises;

public class HelperClass_Barcodenew {
    String conno,date,couriername;
    public HelperClass_Barcodenew() {
    }

    public HelperClass_Barcodenew(String date, String conno,String couriername) {
        this.conno = conno;
        this.date = date;
        this.couriername = couriername;
    }

    public String getConno() {
        return conno;
    }

    public void setConno(String conno) {
        this.conno = conno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCouriername() {
        return couriername;
    }

    public void setCouriername(String couriername) {
        this.couriername = couriername;
    }
}
