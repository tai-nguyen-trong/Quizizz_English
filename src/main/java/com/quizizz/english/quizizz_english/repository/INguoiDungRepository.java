package com.quizizz.english.quizizz_english.repository;

import com.quizizz.english.quizizz_english.model.NguoiDung;

public interface INguoiDungRepository extends IRepository<NguoiDung> {
    void dangKy(NguoiDung item);
    NguoiDung dangNhap(String email, String matKhau);
}
