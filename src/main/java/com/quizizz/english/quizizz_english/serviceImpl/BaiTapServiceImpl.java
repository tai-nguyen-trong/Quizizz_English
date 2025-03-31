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
        baiTapRepository.addBaiTap(item);
        return true;
    }

    @Override
    public boolean updateBaiTap(BaiTap item) {
        baiTapRepository.updateBaiTap(item);
        return true;
    }

    @Override
    public boolean deleteBaiTap(int item) {
        baiTapRepository.deleteBaiTap(item);
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
