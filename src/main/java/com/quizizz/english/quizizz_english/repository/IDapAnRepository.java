package com.quizizz.english.quizizz_english.repository;

import com.quizizz.english.quizizz_english.dto.CauHoiDTO;
import com.quizizz.english.quizizz_english.model.DapAn;

import java.util.List;

public interface IDapAnRepository extends IRepository<DapAn> {
    List<DapAn> getAllByIdBaiTap(Integer idCauHoi);
}
