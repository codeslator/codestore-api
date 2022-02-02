package com.codestoreapi.core.services;

import com.codestoreapi.core.models.Store;
import com.codestoreapi.vo.StoreVO;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    Optional<List<StoreVO>> getAll();
    Optional<StoreVO> getById(Long id);
    Optional<StoreVO> save(StoreVO storeVO);
    Optional<StoreVO> update(StoreVO storeVO);
    Optional<Long> delete(Long id);
}
