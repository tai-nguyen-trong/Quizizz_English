package com.quizizz.english.quizizz_english.model;

import java.util.List;

public class ChuDe {
    private int id;
    private String tenChuDe;
    private String moTa;
    private String hinhAnh;

    private List<BaiTap> baiTap;

    public ChuDe() {
    }

    public ChuDe(int id, String tenChuDe, String moTa, String hinhAnh) {
        this.id = id;
        this.tenChuDe = tenChuDe;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    @Override
    public String toString() {
        return "ChuDe{id=" + id + ", name='" + tenChuDe + "', mota='" + moTa + "', hinhanh='" + hinhAnh + "'}";
    }
}
