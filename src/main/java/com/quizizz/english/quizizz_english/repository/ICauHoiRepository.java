package com.quizizz.english.quizizz_english.repository;

import com.quizizz.english.quizizz_english.dto.CauHoiDTO;
import com.quizizz.english.quizizz_english.model.BaiTap;
import com.quizizz.english.quizizz_english.model.CauHoi;

import java.util.List;

public interface ICauHoiRepository{
    int insert(CauHoi item);

    boolean update(CauHoi item);


    boolean delete(int idCauHoi);


    List<CauHoiDTO> getAll();


    CauHoi getById(int ID);

    List<CauHoiDTO> getAllByIdBaiTap(Integer idBaiTap);
    CauHoi getCauHoiMoiNhat();
}
