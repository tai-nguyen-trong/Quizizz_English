package com.quizizz.english.quizizz_english.model;

import java.util.List;

public class CauHoi {
    private int id;
    private String maCauHoi;
    private String tenCauHoi;
    private int idBaiTap;

    private BaiTap baiTap;
    private List<DapAn> dapAn;
    private DapAnNguoiDung dapAnNguoiDung;

    public CauHoi() {
    }

    public CauHoi(int id, String maCauHoi, String tenCauHoi, int idBaiTap) {
        this.id = id;
        this.maCauHoi = maCauHoi;
        this.tenCauHoi = tenCauHoi;
        this.idBaiTap = idBaiTap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaCauHoi() {
        return maCauHoi;
    }

    public void setMaCauHoi(String maCauHoi) {
        this.maCauHoi = maCauHoi;
    }

    public String getTenCauHoi() {
        return tenCauHoi;
    }

    public void setTenCauHoi(String tenCauHoi) {
        this.tenCauHoi = tenCauHoi;
    }

    public int getIdBaiTap() {
        return idBaiTap;
    }

    public void setIdBaiTap(int idBaiTap) {
        this.idBaiTap = idBaiTap;
    }
}
