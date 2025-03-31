package com.quizizz.english.quizizz_english.serviceImpl;

import com.quizizz.english.quizizz_english.model.ChuDe;
import com.quizizz.english.quizizz_english.repository.IChuDeRepository;
import com.quizizz.english.quizizz_english.service.IChuDeService;

import java.util.List;

public class ChuDeServiceImpl implements IChuDeService {
    private final IChuDeRepository chuDeRepository;

    public ChuDeServiceImpl(IChuDeRepository chuDeRepository) {
        this.chuDeRepository = chuDeRepository;
    }

    @Override
    public void addChuDe(ChuDe item) {
        chuDeRepository.insert(item);
    }

    @Override
    public void updateChuDe(ChuDe item) {
        chuDeRepository.update(item);
    }

    @Override
    public void deleteChuDe(ChuDe item) {
        chuDeRepository.delete(item);
    }

    @Override
    public List<ChuDe> getAllChuDe() {
        return chuDeRepository.getAll();
    }

    @Override
    public ChuDe getChuDeById(int id) {
        return chuDeRepository.getById(id);
    }
}
