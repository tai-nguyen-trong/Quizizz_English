package com.quizizz.english.quizizz_english.model;

public class LichSuLamBai {
    private int id;
    private Double diem;
    private int idNguoiDung;
    private int idBaiTap;
    private int idChuDe;

    private NguoiDung nguoiDung;
    private BaiTap baiTap;
    private ChuDe chuDe;

    public LichSuLamBai() {
    }

    public LichSuLamBai(int id, Double diem, int idNguoiDung, int idBaiTap, int idChuDe) {
        this.id = id;
        this.diem = diem;
        this.idNguoiDung = idNguoiDung;
        this.idBaiTap = idBaiTap;
        this.idChuDe = idChuDe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getDiem() {
        return diem;
    }

    public void setDiem(Double diem) {
        this.diem = diem;
    }

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public int getIdBaiTap() {
        return idBaiTap;
    }

    public void setIdBaiTap(int idBaiTap) {
        this.idBaiTap = idBaiTap;
    }

    public int getIdChuDe() {
        return idChuDe;
    }

    public void setIdChuDe(int idChuDe) {
        this.idChuDe = idChuDe;
    }
}