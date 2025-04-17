package com.quizizz.english.quizizz_english.serviceImpl;

import com.quizizz.english.quizizz_english.dto.CauHoiDTO;
import com.quizizz.english.quizizz_english.model.CauHoi;
import com.quizizz.english.quizizz_english.repository.ICauHoiRepository;
import com.quizizz.english.quizizz_english.service.ICauHoiService;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CauHoiServiceImpl implements ICauHoiService {
    private final ICauHoiRepository cauHoiRepository;

    public CauHoiServiceImpl(ICauHoiRepository cauHoiRepository) {
        this.cauHoiRepository = cauHoiRepository;
    }

    @Override
    public int addCauHoi(CauHoi item) {
        // Kiểm tra dữ liệu đầu vào
        if (item.getTenCauHoi() == null || item.getTenCauHoi().isEmpty() || item.getIdBaiTap() <= 0) {
            return HttpServletResponse.SC_BAD_REQUEST;
        }
        var cauHoiOld = cauHoiRepository.getCauHoiMoiNhat();
        String prefix = "CH";  // Tiền tố "BT"
        String maBaiTap = prefix + String.format("%04d", cauHoiOld.getId() + 1);
        item.setMaCauHoi(maBaiTap);
        return cauHoiRepository.insert(item);
    }

    @Override
    public boolean updateCauHoi(CauHoi item) {
        if (item.getTenCauHoi() == null || item.getTenCauHoi().isEmpty() || item.getId() <= 0) {
            return false;
        }
        return cauHoiRepository.update(item);
    }

    @Override
    public boolean deleteCauHoi(int idCauHoi) {
        return cauHoiRepository.delete(idCauHoi);
    }

    @Override
    public List<CauHoiDTO> getAllCauHoi(int start, int length, String search, String sortColumn, String sortDirection,String idBaiTap) {
        List<CauHoiDTO> danhCauHoi = (idBaiTap != null && !idBaiTap.isEmpty())
                ? cauHoiRepository.getAllByIdBaiTap(Integer.valueOf(idBaiTap))
                : cauHoiRepository.getAll();
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
                case "maBaiTap" -> Comparator.comparing(CauHoiDTO::getMaBaiTap);
                case "tenBaiTap" -> Comparator.comparing(CauHoiDTO::getTenBaiTap);
                case "maCauHoi" -> Comparator.comparing(CauHoiDTO::getMaCauHoi);
                case "tenCauHoi" -> Comparator.comparing(CauHoiDTO::getTenCauHoi);
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

    @Override
    public List<CauHoi> getCauHoiVaDapAnTheoBaiTap(int idBaiTap) {
        return cauHoiRepository.getCauHoiVaDapAnTheoBaiTap(idBaiTap);
    }
}
