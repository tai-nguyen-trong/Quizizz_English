package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.model.DapAn;
import java.util.List;

public interface IDapAnService {
    void addDapAn(DapAn item);
    void updateDapAn(DapAn item);
    void deleteDapAn(DapAn item);
    List<DapAn> getAllDapAn();
    DapAn getDapAnById(int id);
}
