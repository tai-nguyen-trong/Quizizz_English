package com.quizizz.english.quizizz_english.serviceImpl;

import com.quizizz.english.quizizz_english.dto.BaiTapDTO;
import com.quizizz.english.quizizz_english.model.BaiTap;
import com.quizizz.english.quizizz_english.repository.IBaiTapRepository;
import com.quizizz.english.quizizz_english.service.IBaiTapService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BaiTapServiceImpl implements IBaiTapService {
    private final IBaiTapRepository baiTapRepository;

    public BaiTapServiceImpl(IBaiTapRepository baiTapRepository) {
        this.baiTapRepository = baiTapRepository;
    }

    @Override
    public boolean addBaiTap(BaiTap item) {
        try{
            // Kiểm tra dữ liệu đầu vào
            if (item.getTenBaiTap() == null || item.getTenBaiTap().isEmpty()) {
                return false;
            }
            var baitapold = baiTapRepository.getBaiTapMoiNhat();
            String prefix = "BT";  // Tiền tố "BT"
            String maBaiTap = prefix + String.format("%04d", baitapold.getId() + 1);
            item.setMaBaiTap(maBaiTap);
            boolean isSuccess =baiTapRepository.addBaiTap(item);
            if(isSuccess){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean updateBaiTap(BaiTap item) {
        if (item.getTenBaiTap() == null || item.getTenBaiTap().isEmpty()) {
            return false;
        }
        baiTapRepository.updateBaiTap(item);
        return true;
    }

    @Override
    public boolean deleteBaiTap(int idBaiTap) {
        baiTapRepository.deleteBaiTap(idBaiTap);
        return true;
    }

    @Override
    public List<BaiTapDTO> getAllBaiTap(int start, int length, String search, String sortColumn, String sortDirection) {
        List<BaiTapDTO> list = baiTapRepository.getAllBaiTap();
        // 🔹 Xử lý tìm kiếm (lọc danh sách theo từ khóa)
        if (search != null && !search.isEmpty()) {
            String lowerSearch = search.toLowerCase();
            list = list.stream()
                    .filter(bt -> bt.getTenBaiTap().toLowerCase().contains(lowerSearch) ||
                            bt.getMaBaiTap().toLowerCase().contains(lowerSearch))
                    .collect(Collectors.toList());
        }
        // 🔹 Xử lý sắp xếp
        if (sortColumn != null && sortDirection != null) {
            Comparator<BaiTapDTO> comparator = switch (sortColumn) {
                case "id" -> Comparator.comparing(BaiTapDTO::getId);
                case "maBaiTap" -> Comparator.comparing(BaiTapDTO::getMaBaiTap);
                case "tenBaiTap" -> Comparator.comparing(BaiTapDTO::getTenBaiTap);
                case "thoiGianLamBai" -> Comparator.comparing(BaiTapDTO::getThoiGianLamBai);
                case "tenCapDo" -> Comparator.comparing(BaiTapDTO::getTenCapDo);
                case "tenChuDe" -> Comparator.comparing(BaiTapDTO::getTenChuDe);
                default -> Comparator.comparing(BaiTapDTO::getId);
            };

            if ("desc".equalsIgnoreCase(sortDirection)) {
                comparator = comparator.reversed();
            }

            list = list.stream().sorted(comparator).collect(Collectors.toList());
        }
        // 🔹 Xử lý phân trang
        int totalRecords = list.size();
        int endIndex = Math.min(start + length, totalRecords);
        list = list.subList(start, endIndex);

        return list;
    }

    @Override
    public BaiTap getBaiTapById(int id) {
        return baiTapRepository.getBaiTapById(id);
    }
    public int getTotalRecords() {
        return baiTapRepository.getAllBaiTap().size();
    }

    @Override
    public List<BaiTap> DanhSachBaiTap() {
        List<BaiTapDTO> list = baiTapRepository.getAllBaiTap();
        List<BaiTap> baitaps = new ArrayList<>();
        for (BaiTapDTO baitap : list) {
            BaiTap baitap1 = new BaiTap(
                    baitap.getId(),
                    baitap.getMaBaiTap(),
                    baitap.getTenBaiTap()
            );
            baitaps.add(baitap1);
        }
        return baitaps;
    }
}
