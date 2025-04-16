package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.model.LichSuLamBai;
import java.util.List;
import java.util.Map;

public interface ILichSuLamBaiService {
    void addLichSuLamBai(int idNguoiDung, int idBaiTap, int idChuDe, Map<Integer, Integer> cauHoiVaDapAn);
    void updateLichSuLamBai(LichSuLamBai item);
    void deleteLichSuLamBai(LichSuLamBai item);
    List<LichSuLamBai> getAllLichSuLamBai();
    LichSuLamBai getLichSuLamBaiById(int id);
}
