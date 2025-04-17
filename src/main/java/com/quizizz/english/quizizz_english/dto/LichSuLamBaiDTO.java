package com.quizizz.english.quizizz_english.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LichSuLamBaiDTO {
    private int id;
    private Double diem;
    private Double thoiGianLamBai;
    private String tenBaiTap;
    private String tenChuDe;
    private String tenCapDo;
    private int idNguoiDung;
    private int idBaiTap;
    private int idChuDe;
}
