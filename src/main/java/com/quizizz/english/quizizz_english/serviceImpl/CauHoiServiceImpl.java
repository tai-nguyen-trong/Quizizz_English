package com.quizizz.english.quizizz_english.serviceImpl;

import com.quizizz.english.quizizz_english.dto.BaiTapDTO;
import com.quizizz.english.quizizz_english.dto.CauHoiDTO;
import com.quizizz.english.quizizz_english.model.CauHoi;
import com.quizizz.english.quizizz_english.repository.ICauHoiRepository;
import com.quizizz.english.quizizz_english.service.ICauHoiService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CauHoiServiceImpl implements ICauHoiService {
    private final ICauHoiRepository cauHoiRepository;

    public CauHoiServiceImpl(ICauHoiRepository cauHoiRepository) {
        this.cauHoiRepository = cauHoiRepository;
    }

    @Override
    public void addCauHoi(CauHoi item) {
        cauHoiRepository.insert(item);
    }

    @Override
    public void updateCauHoi(CauHoi item) {
        cauHoiRepository.update(item);
    }

    @Override
    public void deleteCauHoi(CauHoi item) {
        cauHoiRepository.delete(item);
    }

    @Override
    public List<CauHoiDTO> getAllCauHoi(int start, int length, String search, String sortColumn, String sortDirection) {
        List<CauHoiDTO> danhCauHoi = cauHoiRepository.getAll();
// 🔹 Xử lý tìm kiếm (lọc danh sách theo từ khóa)
        if (search != null && !search.isEmpty()) {
            String lowerSearch = search.toLowerCase();
            danhCauHoi = danhCauHoi.stream()
                    .filter(ch -> ch.getTenBaiTap().toLowerCase().contains(lowerSearch)
                            || ch.getMaBaiTap().toLowerCase().contains(lowerSearch)
                            || ch.getMaCauHoi().toLowerCase().contains(lowerSearch)
                            || ch.getTenCauHoi().toLowerCase().contains(lowerSearch))
                    .collect(Collectors.toList());
        }
        // 🔹 Xử lý sắp xếp
        if (sortColumn != null && sortDirection != null) {
            Comparator<CauHoiDTO> comparator = switch (sortColumn) {
                case "id" -> Comparator.comparing(CauHoiDTO::getId);
                case "maCauHoi" -> Comparator.comparing(CauHoiDTO::getMaCauHoi);
                case "tenCauHoi" -> Comparator.comparing(CauHoiDTO::getTenCauHoi);
                case "maBaiTap" -> Comparator.comparing(CauHoiDTO::getMaBaiTap);
                case "tenBaiTap" -> Comparator.comparing(CauHoiDTO::getTenBaiTap);
                default -> Comparator.comparing(CauHoiDTO::getId);
            };

            if ("desc".equalsIgnoreCase(sortDirection)) {
                comparator = comparator.reversed();
            }

            danhCauHoi = danhCauHoi.stream().sorted(comparator).collect(Collectors.toList());
        }
        // 🔹 Xử lý phân trang
        int totalRecords = danhCauHoi.size();
        int endIndex = Math.min(start + length, totalRecords);
        danhCauHoi = danhCauHoi.subList(start, endIndex);
        return danhCauHoi;
    }

    @Override
    public CauHoi getCauHoiById(int id) {
        return cauHoiRepository.getById(id);
    }
}
