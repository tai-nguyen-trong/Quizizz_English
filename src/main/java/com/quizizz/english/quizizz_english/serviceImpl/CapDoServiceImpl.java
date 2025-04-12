package com.quizizz.english.quizizz_english.serviceImpl;

import com.quizizz.english.quizizz_english.model.CapDo;
import com.quizizz.english.quizizz_english.repository.ICapDoRepository;
import com.quizizz.english.quizizz_english.service.ICapDoService;

import java.util.List;

public class CapDoServiceImpl implements ICapDoService {
    private final ICapDoRepository capDoRepository;

    public CapDoServiceImpl(ICapDoRepository capDoRepository) {
        this.capDoRepository = capDoRepository;
    }

    @Override
    public boolean addCapDo(CapDo item) {
        return capDoRepository.insert(item);
    }

    @Override
    public boolean updateCapDo(CapDo item) {
        return capDoRepository.update(item);
    }

    @Override
    public boolean deleteCapDo(int idCapDo) {
        return capDoRepository.delete(idCapDo);
    }

    @Override
    public List<CapDo> getAllCapDo() {
        return capDoRepository.getAll();
    }

    @Override
    public CapDo getCapDoById(int id) {
        return capDoRepository.getById(id);
    }
}
