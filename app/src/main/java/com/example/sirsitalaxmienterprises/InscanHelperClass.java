package com.example.sirsitalaxmienterprises;

public class InscanHelperClass {
    String date,conno;

    public InscanHelperClass() {

    }

    public InscanHelperClass(String date, String conno) {
        this.date = date;
        this.conno = conno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getConno() {
        return conno;
    }

    public void setConno(String conno) {
        this.conno = conno;
    }
}
