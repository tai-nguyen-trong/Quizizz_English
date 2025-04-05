package com.quizizz.english.quizizz_english.repository;

import com.quizizz.english.quizizz_english.dto.CauHoiDTO;
import com.quizizz.english.quizizz_english.model.CauHoi;

import java.util.List;

public interface ICauHoiRepository{
    void insert(CauHoi item);

    void update(CauHoi item);


    void delete(CauHoi item);


    List<CauHoiDTO> getAll();


    CauHoi getById(int ID);
}
