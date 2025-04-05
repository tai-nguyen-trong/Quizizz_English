package com.quizizz.english.quizizz_english.dto;

import com.quizizz.english.quizizz_english.model.BaiTap;
import com.quizizz.english.quizizz_english.model.DapAn;
import com.quizizz.english.quizizz_english.model.DapAnNguoiDung;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CauHoiDTO {
    private int id;
    private String maCauHoi;
    private String tenCauHoi;
    private int idBaiTap;
    private String maBaiTap;
    private String tenBaiTap;
}
