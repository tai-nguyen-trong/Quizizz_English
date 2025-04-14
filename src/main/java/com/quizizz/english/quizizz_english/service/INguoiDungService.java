package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.model.NguoiDung;
import java.util.List;

public interface INguoiDungService {
    void addNguoiDung(NguoiDung item);
    void updateMatKhau(int id, String matKhauMoi);
    boolean updateNguoiDung(NguoiDung item);
    void deleteNguoiDung(NguoiDung item);
    List<NguoiDung> getAllNguoiDung();
    NguoiDung getNguoiDungById(int id);
    void dangKy(NguoiDung item);
    NguoiDung dangNhap(String email, String matKhau);
}
