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
    public boolean addDapAn(DapAn item) {
        boolean isSucess=dapAnRepository.insert(item);
        if (isSucess) return true;
        else return false;
    }

    @Override
    public boolean updateDapAn(DapAn item) {
        if (item.getId() != 0){
            return dapAnRepository.update(item);
        }else {
            return dapAnRepository.insert(item);
        }
    }

    @Override
    public boolean deleteDapAn(int idDapAn) {
        boolean isSucess=dapAnRepository.delete(idDapAn);
        if (isSucess) return true;
        else return false;
    }


    @Override
    public List<DapAn> getAllCauHoiByIdBaiTap(Integer idCauHoi) {
        return dapAnRepository.getAllCauHoiByIdBaiTap(idCauHoi);
    }
}
