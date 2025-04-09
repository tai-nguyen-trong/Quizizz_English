package com.quizizz.english.quizizz_english.repository;

import com.quizizz.english.quizizz_english.model.ChuDe;

import java.util.List;

public interface IChuDeRepository{
    boolean insert(ChuDe item);
    boolean update(ChuDe item);
    boolean delete(int idChuDe);
    List<ChuDe> getAll();
    ChuDe getById(int ID);
}
