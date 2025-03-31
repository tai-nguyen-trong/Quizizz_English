package com.quizizz.english.quizizz_english.model;

public class DapAn {
    private int id;
    private String tenDapAn;
    private boolean dapAnDung;
    private int idCauHoi;

    private CauHoi cauHoi;
    private DapAnNguoiDung dapAnNguoiDung;

    public DapAn() {
    }

    public DapAn(int id, String tenDapAn, boolean dapAnDung, int idCauHoi) {
        this.id = id;
        this.tenDapAn = tenDapAn;
        this.dapAnDung = dapAnDung;
        this.idCauHoi = idCauHoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDapAn() {
        return tenDapAn;
    }

    public void setTenDapAn(String tenDapAn) {
        this.tenDapAn = tenDapAn;
    }

    public boolean getDapAnDung() {
        return dapAnDung;
    }

    public void setDapAnDung(boolean dapAnDung) {
        this.dapAnDung = dapAnDung;
    }

    public int getIdCauHoi() {
        return idCauHoi;
    }

    public void setIdCauHoi(int idCauHoi) {
        this.idCauHoi = idCauHoi;
    }
}
