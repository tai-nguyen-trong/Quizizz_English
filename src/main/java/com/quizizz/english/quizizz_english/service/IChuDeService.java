package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.model.ChuDe;
import java.util.List;

public interface IChuDeService {
    boolean addChuDe(ChuDe item);
    boolean updateChuDe(ChuDe item);
    boolean deleteChuDe(int idChuDe);
    List<ChuDe> getAllChuDe();
    ChuDe getChuDeById(int id);
}
