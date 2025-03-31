package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.model.DapAnNguoiDung;
import java.util.List;

public interface IDapAnNguoiDungService {
    void addDapAnNguoiDung(DapAnNguoiDung item);
    void updateDapAnNguoiDung(DapAnNguoiDung item);
    void deleteDapAnNguoiDung(DapAnNguoiDung item);
    List<DapAnNguoiDung> getAllDapAnNguoiDung();
    DapAnNguoiDung getDapAnNguoiDungById(int id);
}
