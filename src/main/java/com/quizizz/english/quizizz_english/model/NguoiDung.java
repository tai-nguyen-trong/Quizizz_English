package com.quizizz.english.quizizz_english.model;

import java.util.List;

public class NguoiDung {
    private int id;
    private String hoVaTen;
    private int tuoi;
    private String email;
    private String matKhau;
    private String soDienThoai;
    private String vaiTro;

    private List<LichSuLamBai> lichSuLamBai;

    public NguoiDung() {
    }

    public NguoiDung(int id, String hoVaTen, int tuoi, String email, String matKhau, String soDienThoai, String vaiTro) {
        this.id = id;
        this.hoVaTen = hoVaTen;
        this.tuoi = tuoi;
        this.email = email;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
        this.vaiTro = vaiTro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }
}
