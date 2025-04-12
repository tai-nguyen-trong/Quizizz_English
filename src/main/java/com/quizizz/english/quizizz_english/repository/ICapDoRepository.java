package com.quizizz.english.quizizz_english.repository;

import com.quizizz.english.quizizz_english.model.CapDo;

import java.util.List;

public interface ICapDoRepository{
    boolean insert(CapDo item);
    boolean update(CapDo item);
    boolean delete(int idCapDo);
    List<CapDo> getAll();
    CapDo getById(int ID);
}
