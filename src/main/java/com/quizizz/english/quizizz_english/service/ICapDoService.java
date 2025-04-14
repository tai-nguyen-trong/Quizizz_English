package com.quizizz.english.quizizz_english.service;
import com.quizizz.english.quizizz_english.model.CapDo;
import java.util.List;

public interface ICapDoService {
    boolean addCapDo(CapDo item);
    boolean updateCapDo(CapDo item);
    boolean deleteCapDo(int idCapDo);
    List<CapDo> getAllCapDo();
    CapDo getCapDoById(int id);
}
