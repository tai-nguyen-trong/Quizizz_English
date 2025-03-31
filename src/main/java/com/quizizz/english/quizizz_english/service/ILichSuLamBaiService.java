package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.model.LichSuLamBai;
import java.util.List;

public interface ILichSuLamBaiService {
    void addLichSuLamBai(LichSuLamBai item);
    void updateLichSuLamBai(LichSuLamBai item);
    void deleteLichSuLamBai(LichSuLamBai item);
    List<LichSuLamBai> getAllLichSuLamBai();
    LichSuLamBai getLichSuLamBaiById(int id);
}
