package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.model.CauHoi;
import java.util.List;

public interface ICauHoiService {
    void addCauHoi(CauHoi item);
    void updateCauHoi(CauHoi item);
    void deleteCauHoi(CauHoi item);
    List<CauHoi> getAllCauHoi();
    CauHoi getCauHoiById(int id);
}
