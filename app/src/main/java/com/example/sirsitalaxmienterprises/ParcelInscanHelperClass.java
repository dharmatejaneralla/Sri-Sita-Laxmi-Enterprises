package com.example.sirsitalaxmienterprises;

public class ParcelInscanHelperClass {
    String recieveddate,conno,pcs,wt,couriername;
    public ParcelInscanHelperClass() {
    }

    public ParcelInscanHelperClass(String recieveddate, String conno, String pcs, String wt, String couriername) {
        this.recieveddate = recieveddate;
        this.conno = conno;
        this.pcs = pcs;
        this.wt = wt;
        this.couriername  =couriername;
    }
    public String getCouriername() {
        return couriername;
    }

    public void setCouriername(String couriername) {
        this.couriername = couriername;
    }

    public String getRecieveddate() {
        return recieveddate;
    }

    public void setRecieveddate(String recieveddate) {
        this.recieveddate = recieveddate;
    }

    public String getConno() {
        return conno;
    }

    public void setConno(String conno) {
        this.conno = conno;
    }

    public String getPcs() {
        return pcs;
    }

    public void setPcs(String pcs) {
        this.pcs = pcs;
    }

    public String getWt() {
        return wt;
    }

    public void setWt(String wt) {
        this.wt = wt;
    }
}

