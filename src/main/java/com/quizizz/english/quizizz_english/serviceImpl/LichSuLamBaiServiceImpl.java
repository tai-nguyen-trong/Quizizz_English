package com.quizizz.english.quizizz_english.serviceImpl;

import com.quizizz.english.quizizz_english.dto.LichSuLamBaiDTO;
import com.quizizz.english.quizizz_english.model.LichSuLamBai;
import com.quizizz.english.quizizz_english.repository.ILichSuLamBaiRepository;
import com.quizizz.english.quizizz_english.service.ILichSuLamBaiService;

import java.util.List;

public class LichSuLamBaiServiceImpl implements ILichSuLamBaiService {
    private final ILichSuLamBaiRepository lichSuLamBaiRepository;

    public LichSuLamBaiServiceImpl(ILichSuLamBaiRepository lichSuLamBaiRepository) {
        this.lichSuLamBaiRepository = lichSuLamBaiRepository;
    }

//    @Override
//    public void addLichSuLamBai(LichSuLamBai item) {
//        lichSuLamBaiRepository.insert(item);
//    }
//
//    @Override
//    public void updateLichSuLamBai(LichSuLamBai item) {
//        lichSuLamBaiRepository.update(item);
//    }
//
//    @Override
//    public void deleteLichSuLamBai(LichSuLamBai item) {
//        lichSuLamBaiRepository.delete(item);
//    }

    @Override
    public List<LichSuLamBaiDTO> getAllLichSuLamBai() {
        return lichSuLamBaiRepository.getAllLichSuLamBai();
    }
//
//    @Override
//    public LichSuLamBai getLichSuLamBaiById(int id) {
//        return lichSuLamBaiRepository.getById(id);
//    }
}
