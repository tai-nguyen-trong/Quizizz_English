package com.quizizz.english.quizizz_english.model;

import com.quizizz.english.quizizz_english.dto.BaiTapDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class BaiTap {
    private int id;
    private String maBaiTap;
    private String tenBaiTap;
    private double thoiGianLamBai;
    private int idChuDe;
    private int idCapDo;

    private List<LichSuLamBai> lichSuLamBai;
    private ChuDe chuDe;
    private CapDo capDo;
    private List<CauHoi> cauHoi;
    private DapAnNguoiDung dapAnNguoiDung;

    public BaiTap() {
    }

    public BaiTap(int id, String maBaiTap, String tenBaiTap, double thoiGianLamBai, int idChuDe, int idCapDo) {
        this.id = id;
        this.maBaiTap = maBaiTap;
        this.tenBaiTap = tenBaiTap;
        this.thoiGianLamBai = thoiGianLamBai;
        this.idChuDe = idChuDe;
        this.idCapDo = idCapDo;
    }

    public BaiTap(int id, String maBaiTap,String tenBaiTap) {
        this.id = id;
        this.maBaiTap = maBaiTap;
        this.tenBaiTap = tenBaiTap;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaBaiTap() {
        return maBaiTap;
    }

    public void setMaBaiTap(String maBaiTap) {
        this.maBaiTap = maBaiTap;
    }

    public String getTenBaiTap() {
        return tenBaiTap;
    }

    public void setTenBaiTap(String tenBaiTap) {
        this.tenBaiTap = tenBaiTap;
    }

    public double getThoiGianLamBai() {
        return thoiGianLamBai;
    }

    public void setThoiGianLamBai(double thoiGianLamBai) {
        this.thoiGianLamBai = thoiGianLamBai;
    }

    public int getIdChuDe() {
        return idChuDe;
    }

    public void setIdChuDe(int idChuDe) {
        this.idChuDe = idChuDe;
    }

    public int getIdCapDo() {
        return idCapDo;
    }

    public void setIdCapDo(int idCapDo) {
        this.idCapDo = idCapDo;
    }
}
