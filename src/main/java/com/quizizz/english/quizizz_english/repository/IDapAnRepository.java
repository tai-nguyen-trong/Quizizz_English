package com.quizizz.english.quizizz_english.repository;

import com.quizizz.english.quizizz_english.dto.CauHoiDTO;
import com.quizizz.english.quizizz_english.model.DapAn;

import java.util.List;

public interface IDapAnRepository{
    boolean insert(DapAn item);
    boolean delete(int idDapAn);
    boolean update(DapAn item);
    List<DapAn> getAllCauHoiByIdBaiTap(Integer idCauHoi);
}
