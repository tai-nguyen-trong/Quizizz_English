package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.model.NguoiDung;
import java.util.List;

public interface INguoiDungService {
    void addNguoiDung(NguoiDung item);
    void updateNguoiDung(NguoiDung item);
    void deleteNguoiDung(NguoiDung item);
    List<NguoiDung> getAllNguoiDung();
    NguoiDung getNguoiDungById(int id);
    NguoiDung dangNhap(String email, String matKhau);
}
