package com.quizizz.english.quizizz_english.model;

import java.util.List;

public class CapDo {
    private int id;
    private String tenCapDo;

    private List<BaiTap> baiTap;

    public CapDo() {
    }

    public CapDo(int id, String tenCapDo) {
        this.id = id;
        this.tenCapDo = tenCapDo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenCapDo() {
        return tenCapDo;
    }

    public void setTenCapDo(String tenCapDo) {
        this.tenCapDo = tenCapDo;
    }
}
