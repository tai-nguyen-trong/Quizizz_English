package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.dto.KetQuaDTO;
import com.quizizz.english.quizizz_english.dto.LichSuLamBaiDTO;
import com.quizizz.english.quizizz_english.model.LichSuLamBai;
import java.util.List;
import java.util.Map;

public interface ILichSuLamBaiService {
    int addLichSuLamBai(int idNguoiDung, int idBaiTap, int idChuDe, Map<Integer, Integer> cauHoiVaDapAn);
    void updateLichSuLamBai(LichSuLamBai item);
    void deleteLichSuLamBai(LichSuLamBai item);
    List<LichSuLamBai> getAllLichSuLamBai();
    LichSuLamBai getLichSuLamBaiById(int id);
    List<KetQuaDTO> getKetQuaLamBai(int idLichSu,int idBaiTap);
}
