package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.model.CapDo;
import java.util.List;

public interface ICapDoService {
    void addCapDo(CapDo item);
    void updateCapDo(CapDo item);
    void deleteCapDo(CapDo item);
    List<CapDo> getAllCapDo();
    CapDo getCapDoById(int id);
}
