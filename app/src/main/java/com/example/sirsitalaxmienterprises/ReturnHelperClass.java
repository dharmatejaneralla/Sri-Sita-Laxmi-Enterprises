package com.example.sirsitalaxmienterprises;

public class ReturnHelperClass {
    String returndate,returnmsfno,returnconno,returnreason;

    public ReturnHelperClass(String returndate, String returnmsfno, String returnconno, String returnreason) {
        this.returndate = returndate;
        this.returnmsfno = returnmsfno;
        this.returnconno = returnconno;
        this.returnreason = returnreason;
    }

    public ReturnHelperClass() {
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

    public String getReturnmsfno() {
        return returnmsfno;
    }

    public void setReturnmsfno(String returnmsfno) {
        this.returnmsfno = returnmsfno;
    }

    public String getReturnconno() {
        return returnconno;
    }

    public void setReturnconno(String returnconno) {
        this.returnconno = returnconno;
    }

    public String getReturnreason() {
        return returnreason;
    }

    public void setReturnreason(String returnreason) {
        this.returnreason = returnreason;
    }
}

