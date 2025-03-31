package com.quizizz.english.quizizz_english.serviceImpl;

import com.quizizz.english.quizizz_english.model.DapAn;
import com.quizizz.english.quizizz_english.repository.IDapAnRepository;
import com.quizizz.english.quizizz_english.service.IDapAnService;

import java.util.List;

public class DapAnServiceImpl implements IDapAnService {
    private final IDapAnRepository dapAnRepository;

    public DapAnServiceImpl(IDapAnRepository dapAnRepository) {
        this.dapAnRepository = dapAnRepository;
    }

    @Override
    public void addDapAn(DapAn item) {
        dapAnRepository.insert(item);
    }

    @Override
    public void updateDapAn(DapAn item) {
        dapAnRepository.update(item);
    }

    @Override
    public void deleteDapAn(DapAn item) {
        dapAnRepository.delete(item);
    }

    @Override
    public List<DapAn> getAllDapAn() {
        return dapAnRepository.getAll();
    }

    @Override
    public DapAn getDapAnById(int id) {
        return dapAnRepository.getById(id);
    }
}
