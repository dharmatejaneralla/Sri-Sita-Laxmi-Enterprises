package com.example.sirsitalaxmienterprises;

public class PodHelperclass {
    String deldate,conno,recname;

    public PodHelperclass() {

    }

    public PodHelperclass(String deldate, String conno,String recname) {
        this.deldate = deldate;
        this.conno = conno;
        this.recname = recname;
    }

    public String getDeldate() {
        return deldate;
    }

    public void setDeldate(String date) {
        this.deldate = date;
    }

    public String getConno() {
        return conno;
    }

    public void setConno(String conno) {
        this.conno = conno;
    }
    public  String getRecname(){return recname;}
    public  void setRecname(){this.recname = recname;}
}
