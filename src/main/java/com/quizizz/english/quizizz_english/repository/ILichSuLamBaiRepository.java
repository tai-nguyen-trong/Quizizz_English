package com.quizizz.english.quizizz_english.repository;

import com.quizizz.english.quizizz_english.dto.LichSuLamBaiDTO;
import com.quizizz.english.quizizz_english.model.LichSuLamBai;

import java.util.List;


public interface ILichSuLamBaiRepository{
    List<LichSuLamBaiDTO> getAllLichSuLamBai();
    int insert(LichSuLamBai item);
    boolean delete(int idDapAn);
    boolean update(LichSuLamBai item);
    List<LichSuLamBai> getAll();
    LichSuLamBai getById(int id);
}
