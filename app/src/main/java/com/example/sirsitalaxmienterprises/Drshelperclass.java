package com.example.sirsitalaxmienterprises;

public  class Drshelperclass {
    String drsdate,conno,drsno,area;

    public Drshelperclass() {

    }

    public Drshelperclass(String drsdate, String drsno, String conno, String area) {
        this.drsdate = drsdate;
        this.drsno = drsno;
        this.conno = conno;
        this.area = area;
    }

    public String getDrsdate() {
        return drsdate;
    }

    public void setDrsdate(String drsdate) {
        this.drsdate = drsdate;
    }

    public String getConno() {
        return conno;
    }

    public void setConno(String conno) {
        this.conno = conno;
    }

    public String getDrsno() {
        return drsno;
    }

    public void setDrsno(String drsno) {
        this.drsno = drsno;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
