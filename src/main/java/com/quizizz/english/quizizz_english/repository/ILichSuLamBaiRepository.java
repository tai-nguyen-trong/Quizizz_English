package com.quizizz.english.quizizz_english.repository;

import com.quizizz.english.quizizz_english.dto.BaiTapDTO;
import com.quizizz.english.quizizz_english.dto.LichSuLamBaiDTO;
import com.quizizz.english.quizizz_english.model.LichSuLamBai;

import java.util.List;

public interface ILichSuLamBaiRepository {
    List<LichSuLamBaiDTO> getAllLichSuLamBai();

}
