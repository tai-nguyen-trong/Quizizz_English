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
    public void addCapDo(CapDo item) {
        capDoRepository.insert(item);
    }

    @Override
    public void updateCapDo(CapDo item) {
        capDoRepository.update(item);
    }

    @Override
    public void deleteCapDo(CapDo item) {
        capDoRepository.delete(item);
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
