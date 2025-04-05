package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.dto.BaiTapDTO;
import com.quizizz.english.quizizz_english.model.BaiTap;
import java.util.List;

public interface IBaiTapService {
    List<BaiTapDTO> getAllBaiTap(int start, int length, String search, String sortColumn, String sortDirection);   // Lấy danh sách tất cả bài tập
    BaiTap getBaiTapById(int id);  // Lấy bài tập theo ID
    boolean addBaiTap(BaiTap baiTap); // Thêm bài tập mới
    boolean updateBaiTap(BaiTap baiTap); // Cập nhật bài tập
    boolean deleteBaiTap(int id);  // Xóa bài tập
    int getTotalRecords();
    List<BaiTap> DanhSachBaiTap();
}
