package com.quizizz.english.quizizz_english.serviceImpl;

import com.quizizz.english.quizizz_english.model.DapAnNguoiDung;
import com.quizizz.english.quizizz_english.repository.IDapAnNguoiDungRepository;
import com.quizizz.english.quizizz_english.service.IDapAnNguoiDungService;

import java.util.List;

public class DapAnNguoiDungServiceImpl implements IDapAnNguoiDungService {
    private final IDapAnNguoiDungRepository dapAnNguoiDungRepository;

    public DapAnNguoiDungServiceImpl(IDapAnNguoiDungRepository dapAnNguoiDungRepository) {
        this.dapAnNguoiDungRepository = dapAnNguoiDungRepository;
    }

    @Override
    public void addDapAnNguoiDung(DapAnNguoiDung item) {
        dapAnNguoiDungRepository.insert(item);
    }

    @Override
    public void updateDapAnNguoiDung(DapAnNguoiDung item) {
        dapAnNguoiDungRepository.update(item);
    }

    @Override
    public void deleteDapAnNguoiDung(DapAnNguoiDung item) {
        dapAnNguoiDungRepository.delete(item);
    }

    @Override
    public List<DapAnNguoiDung> getAllDapAnNguoiDung() {
        return dapAnNguoiDungRepository.getAll();
    }

    @Override
    public DapAnNguoiDung getDapAnNguoiDungById(int id) {
        return dapAnNguoiDungRepository.getById(id);
    }
}
