package com.quizizz.english.quizizz_english.dto;

public class KetQuaDTO {
    private int idCauHoi;
    private String dapAnNguoiDungChon;
    private boolean cauDung;
    private String dapAnDung;

    public KetQuaDTO() {
    }

    public KetQuaDTO(int idCauHoi, String dapAnNguoiDungChon, boolean cauDung, String dapAnDung) {
        this.idCauHoi = idCauHoi;
        this.dapAnNguoiDungChon = dapAnNguoiDungChon;
        this.cauDung = cauDung;
        this.dapAnDung = dapAnDung;
    }

    public int getIdCauHoi() {
        return idCauHoi;
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
