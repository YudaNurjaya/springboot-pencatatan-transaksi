package com.enigma.livecodeecommerce.service;

import org.springframework.data.domain.Pageable;

public interface IService<T> {
    T save(T save);
    T update(T update, Integer id);
    void delete(Integer id);
    Iterable<T> findAll(Pageable pageable);
}
