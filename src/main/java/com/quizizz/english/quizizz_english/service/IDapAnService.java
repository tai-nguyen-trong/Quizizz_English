package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.model.DapAn;
import java.util.List;

public interface IDapAnService {
    boolean addDapAn(DapAn item);
    boolean updateDapAn(DapAn item);
    boolean deleteDapAn(int idDapAn);
    List<DapAn> getAllCauHoiByIdBaiTap(Integer idCauHoi);
}
