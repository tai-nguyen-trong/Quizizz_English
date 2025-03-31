package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.model.ChuDe;
import java.util.List;

public interface IChuDeService {
    void addChuDe(ChuDe item);
    void updateChuDe(ChuDe item);
    void deleteChuDe(ChuDe item);
    List<ChuDe> getAllChuDe();
    ChuDe getChuDeById(int id);
}
