package com.quizizz.english.quizizz_english.repository;

import com.quizizz.english.quizizz_english.dto.BaiTapDTO;

import java.util.List;

public interface IRepository<T> {
    void insert(T item);
    void update(T item);
    void delete(T item);
    List<T> getAll();
    T getById(int ID);
}
