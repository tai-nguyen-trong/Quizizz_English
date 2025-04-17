package com.quizizz.english.quizizz_english.model;

public class DapAnNguoiDung {
    private int id;
    private boolean cauDung;
    private int idBaiTap;
    private int idCauHoi;
    private int idDapAn;
    private int idLichSuLamBai;

    private BaiTap baiTap;
    private CauHoi cauHoi;
    private DapAn dapAn;
    private LichSuLamBai lichSuLamBai;

    public DapAnNguoiDung() {
    }

    public DapAnNguoiDung(int id, boolean cauDung, int idBaiTap, int idCauHoi, int idDapAn, int idLichSuLamBai) {
        this.id = id;
        this.cauDung = cauDung;
        this.idBaiTap = idBaiTap;
        this.idCauHoi = idCauHoi;
        this.idDapAn = idDapAn;
        this.idLichSuLamBai = idLichSuLamBai;
    }

    public DapAnNguoiDung(int idLichSuLamBai, boolean cauDung, int idBaiTap, int idCauHoi, int idDapAn) {
        this.idLichSuLamBai = idLichSuLamBai;
        this.cauDung = cauDung;
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

    public boolean getCauDung() {
        return cauDung;
    }

    public void setCauDung(boolean cauDung) {
        cauDung = cauDung;
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

    public int getIdLichSuLamBai() {
        return idLichSuLamBai;
    }

    public void setIdLichSuLamBai(int idLichSuLamBai) {
        this.idLichSuLamBai = idLichSuLamBai;
    }
}
