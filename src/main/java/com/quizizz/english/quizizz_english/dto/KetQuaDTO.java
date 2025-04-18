package com.quizizz.english.quizizz_english.dto;

public class KetQuaDTO {
    private int idCauHoi;
    private String tenCauHoi;
    private String tenBaiTap;
    private String tenChuDe;
    private String dapAnNguoiDungChon;
    private boolean cauDung;
    private String dapAnDung;
    private double soDiem;

    public KetQuaDTO() {
    }

    public KetQuaDTO(int idCauHoi,String tenCauHoi, String dapAnNguoiDungChon, boolean cauDung, String dapAnDung) {
        this.idCauHoi = idCauHoi;
        this.tenCauHoi = tenCauHoi;
        this.dapAnNguoiDungChon = dapAnNguoiDungChon;
        this.cauDung = cauDung;
        this.dapAnDung = dapAnDung;
    }

    public KetQuaDTO(int idCauHoi, String tenCauHoi, String tenBaiTap, String tenChuDe, String dapAnNguoiDungChon, boolean cauDung, String dapAnDung, double soDiem) {
        this.idCauHoi = idCauHoi;
        this.tenCauHoi = tenCauHoi;
        this.tenBaiTap = tenBaiTap;
        this.tenChuDe = tenChuDe;
        this.dapAnNguoiDungChon = dapAnNguoiDungChon;
        this.cauDung = cauDung;
        this.dapAnDung = dapAnDung;
        this.soDiem = soDiem;
    }

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

    public String getTenBaiTap() {
        return tenBaiTap;
    }

    public void setTenBaiTap(String tenBaiTap) {
        this.tenBaiTap = tenBaiTap;
    }

    public double getSoDiem() {
        return soDiem;
    }

    public void setSoDiem(double soDiem) {
        this.soDiem = soDiem;
    }

    public int getIdCauHoi() {
        return idCauHoi;
    }

    public String getTenCauHoi() {
        return tenCauHoi;
    }

    public void setTenCauHoi(String tenCauHoi) {
        this.tenCauHoi = tenCauHoi;
    }

    public void setIdCauHoi(int idCauHoi) {
        this.idCauHoi = idCauHoi;
    }

    public String getDapAnNguoiDungChon() {
        return dapAnNguoiDungChon;
    }

    public void setDapAnNguoiDungChon(String dapAnNguoiDungChon) {
        this.dapAnNguoiDungChon = dapAnNguoiDungChon;
    }

    public boolean isCauDung() {
        return cauDung;
    }

    public void setCauDung(boolean cauDung) {
        this.cauDung = cauDung;
    }

    public String getDapAnDung() {
        return dapAnDung;
    }

    public void setDapAnDung(String dapAnDung) {
        this.dapAnDung = dapAnDung;
    }
}
