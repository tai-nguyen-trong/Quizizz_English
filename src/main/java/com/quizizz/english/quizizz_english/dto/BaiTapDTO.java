package com.quizizz.english.quizizz_english.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaiTapDTO {
    private int id;
    private String maBaiTap;
    private String tenBaiTap;
    private Double thoiGianLamBai;
    private String tenCapDo;
    private String tenChuDe;
}
