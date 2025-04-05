package com.quizizz.english.quizizz_english.serviceImpl;

import com.quizizz.english.quizizz_english.dto.BaiTapDTO;
import com.quizizz.english.quizizz_english.model.BaiTap;
import com.quizizz.english.quizizz_english.repository.IBaiTapRepository;
import com.quizizz.english.quizizz_english.service.IBaiTapService;

import java.util.List;

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
    public List<BaiTapDTO> getAllBaiTap() {
        return baiTapRepository.getAllBaiTap();
    }

    @Override
    public BaiTap getBaiTapById(int id) {
        return baiTapRepository.getBaiTapById(id);
    }
}
