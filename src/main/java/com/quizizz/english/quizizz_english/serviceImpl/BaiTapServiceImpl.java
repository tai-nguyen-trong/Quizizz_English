package com.quizizz.english.quizizz_english.serviceImpl;

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
    public void addBaiTap(BaiTap item) {
        baiTapRepository.insert(item);
    }

    @Override
    public void updateBaiTap(BaiTap item) {
        baiTapRepository.update(item);
    }

    @Override
    public void deleteBaiTap(BaiTap item) {
        baiTapRepository.delete(item);
    }

    @Override
    public List<BaiTap> getAllBaiTap() {
        return baiTapRepository.getAll();
    }

    @Override
    public BaiTap getBaiTapById(int id) {
        return baiTapRepository.getById(id);
    }
}
