package com.quizizz.english.quizizz_english.repository;

import com.quizizz.english.quizizz_english.model.NguoiDung;

import java.util.List;

public interface INguoiDungRepository { // extends IRepository<NguoiDung>
    void insert(NguoiDung item);
    void updateMatKhau(int id, String matKhauMoi);
    boolean update(NguoiDung item);
    void delete(NguoiDung item);
    List<NguoiDung> getAll();
    NguoiDung getById(int ID);
    void dangKy(NguoiDung item);
    NguoiDung dangNhap(String email, String matKhau);
}
