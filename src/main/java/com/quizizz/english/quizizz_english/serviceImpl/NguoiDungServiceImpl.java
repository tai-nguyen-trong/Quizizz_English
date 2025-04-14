package com.quizizz.english.quizizz_english.serviceImpl;

import com.quizizz.english.quizizz_english.model.NguoiDung;
import com.quizizz.english.quizizz_english.repository.INguoiDungRepository;
import com.quizizz.english.quizizz_english.service.INguoiDungService;

import java.util.List;

public class NguoiDungServiceImpl implements INguoiDungService {
    private final INguoiDungRepository nguoiDungRepository;

    public NguoiDungServiceImpl(INguoiDungRepository nguoiDungRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
    }

    @Override
    public void addNguoiDung(NguoiDung item) {
        nguoiDungRepository.insert(item);
    }

    @Override
    public void updateMatKhau(int id, String matKhauMoi) {
        nguoiDungRepository.updateMatKhau(id, matKhauMoi);
    }

    @Override
    public boolean updateNguoiDung(NguoiDung item) {
        nguoiDungRepository.update(item);
        return false;
    }

    @Override
    public void deleteNguoiDung(NguoiDung item) {
        nguoiDungRepository.delete(item);
    }

    @Override
    public List<NguoiDung> getAllNguoiDung() {
        return nguoiDungRepository.getAll();
    }

    @Override
    public NguoiDung getNguoiDungById(int id) {
        return nguoiDungRepository.getById(id);
    }

    @Override
    public void dangKy(NguoiDung item) {
        nguoiDungRepository.dangKy(item);
    }

    @Override
    public NguoiDung dangNhap(String email, String matKhau) {
        return nguoiDungRepository.dangNhap(email, matKhau);
    }
}
