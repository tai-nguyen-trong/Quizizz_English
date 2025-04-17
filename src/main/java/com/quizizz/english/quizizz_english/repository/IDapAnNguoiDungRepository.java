package com.quizizz.english.quizizz_english.repository;

import com.quizizz.english.quizizz_english.model.DapAn;
import com.quizizz.english.quizizz_english.model.DapAnNguoiDung;

import java.util.List;

public interface IDapAnNguoiDungRepository{
    boolean insert(DapAnNguoiDung item);
    boolean delete(int idDapAnNguoiDung);
    boolean update(DapAnNguoiDung item);
    List<DapAnNguoiDung> getAll();
    DapAnNguoiDung getById(int id);
}
