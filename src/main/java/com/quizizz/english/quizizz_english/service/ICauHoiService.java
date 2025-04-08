package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.dto.CauHoiDTO;
import com.quizizz.english.quizizz_english.model.CauHoi;
import java.util.List;

public interface ICauHoiService {
    int addCauHoi(CauHoi item);
    void updateCauHoi(CauHoi item);
    void deleteCauHoi(CauHoi item);
    List<CauHoiDTO> getAllCauHoi(int start, int length, String search, String sortColumn, String sortDirection,String idBaiTap);
    CauHoi getCauHoiById(int id);
}
