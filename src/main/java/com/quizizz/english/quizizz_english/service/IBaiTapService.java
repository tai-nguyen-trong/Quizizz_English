package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.model.BaiTap;
import java.util.List;

public interface IBaiTapService {
    void addBaiTap(BaiTap item);
    void updateBaiTap(BaiTap item);
    void deleteBaiTap(BaiTap item);
    List<BaiTap> getAllBaiTap();
    BaiTap getBaiTapById(int id);
}
