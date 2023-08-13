package com.messas.kikikikikikik;

public class Class_Model {
    String p_number,p_u_number,p_u_transcation,givenamount;

    public Class_Model() {
    }

    public String getP_number() {
        return p_number;
    }

    public void setP_number(String p_number) {
        this.p_number = p_number;
    }

    public String getP_u_number() {
        return p_u_number;
    }

    public void setP_u_number(String p_u_number) {
        this.p_u_number = p_u_number;
    }

    public String getP_u_transcation() {
        return p_u_transcation;
    }

    public void setP_u_transcation(String p_u_transcation) {
        this.p_u_transcation = p_u_transcation;
    }

    public String getGivenamount() {
        return givenamount;
    }

    public void setGivenamount(String givenamount) {
        this.givenamount = givenamount;
    }

    public Class_Model(String p_number, String p_u_number, String p_u_transcation, String givenamount) {
        this.p_number = p_number;
        this.p_u_number = p_u_number;
        this.p_u_transcation = p_u_transcation;
        this.givenamount = givenamount;
    }
}
