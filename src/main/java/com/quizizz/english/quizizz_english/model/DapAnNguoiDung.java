package com.quizizz.english.quizizz_english.model;

public class DapAnNguoiDung {
    private int id;
    private boolean CauDung;
    private int idBaiTap;
    private int idCauHoi;
    private int idDapAn;

    private BaiTap baiTap;
    private CauHoi cauHoi;
    private DapAn dapAn;

    public DapAnNguoiDung() {
    }

    public DapAnNguoiDung(int id, boolean cauDung, int idBaiTap, int idCauHoi, int idDapAn) {
        this.id = id;
        CauDung = cauDung;
        this.idBaiTap = idBaiTap;
        this.idCauHoi = idCauHoi;
        this.idDapAn = idDapAn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCauDung() {
        return CauDung;
    }

    public void setCauDung(boolean cauDung) {
        CauDung = cauDung;
    }

    public int getIdBaiTap() {
        return idBaiTap;
    }

    public void setIdBaiTap(int idBaiTap) {
        this.idBaiTap = idBaiTap;
    }

    public int getIdCauHoi() {
        return idCauHoi;
    }

    public void setIdCauHoi(int idCauHoi) {
        this.idCauHoi = idCauHoi;
    }

    public int getIdDapAn() {
        return idDapAn;
    }

    public void setIdDapAn(int idDapAn) {
        this.idDapAn = idDapAn;
    }
}
