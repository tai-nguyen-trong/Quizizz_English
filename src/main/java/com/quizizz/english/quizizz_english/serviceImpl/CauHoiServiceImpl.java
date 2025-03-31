package com.quizizz.english.quizizz_english.serviceImpl;

import com.quizizz.english.quizizz_english.model.CauHoi;
import com.quizizz.english.quizizz_english.repository.ICauHoiRepository;
import com.quizizz.english.quizizz_english.service.ICauHoiService;

import java.util.List;

public class CauHoiServiceImpl implements ICauHoiService {
    private final ICauHoiRepository cauHoiRepository;

    public CauHoiServiceImpl(ICauHoiRepository cauHoiRepository) {
        this.cauHoiRepository = cauHoiRepository;
    }

    @Override
    public void addCauHoi(CauHoi item) {
        cauHoiRepository.insert(item);
    }

    @Override
    public void updateCauHoi(CauHoi item) {
        cauHoiRepository.update(item);
    }

    @Override
    public void deleteCauHoi(CauHoi item) {
        cauHoiRepository.delete(item);
    }

    @Override
    public List<CauHoi> getAllCauHoi() {
        return cauHoiRepository.getAll();
    }

    @Override
    public CauHoi getCauHoiById(int id) {
        return cauHoiRepository.getById(id);
    }
}
