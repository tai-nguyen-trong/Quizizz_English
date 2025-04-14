package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.dto.CauHoiDTO;
import com.quizizz.english.quizizz_english.model.CauHoi;
import java.util.List;

public interface ICauHoiService {
    int addCauHoi(CauHoi item);
    boolean updateCauHoi(CauHoi item);
    boolean deleteCauHoi(int idCauHoi);
    List<CauHoiDTO> getAllCauHoi(int start, int length, String search, String sortColumn, String sortDirection,String idBaiTap);
    CauHoi getCauHoiById(int id);
}
