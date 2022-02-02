package com.codestoreapi.core.services.impl;

import com.codestoreapi.core.models.Store;
import com.codestoreapi.core.repositories.StoreRepository;
import com.codestoreapi.core.services.StoreService;
import com.codestoreapi.vo.StoreVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<List<StoreVO>> getAll() {
        List<Store> stores = storeRepository.findAll();
        List<StoreVO> mappedStores = new ArrayList<>();
        stores.forEach(store -> mappedStores.add(modelMapper.map(store, StoreVO.class)));
        return Optional.of(mappedStores);
    }

    @Override
    public Optional<StoreVO> getById(Long id) {
        StoreVO mappedStore = modelMapper.map(storeRepository.findById(id).get(), StoreVO.class);
        return Optional.of(mappedStore);
    }

    @Override
    public Optional<StoreVO> save(StoreVO storeVO) {
        Store store = modelMapper.map(storeVO, Store.class);
        StoreVO mappedStore = modelMapper.map(storeRepository.save(store), StoreVO.class);
        return Optional.of(mappedStore);
    }

    @Override
    public Optional<StoreVO> update(StoreVO storeVO) {
        Store store = modelMapper.map(storeVO, Store.class);
        StoreVO mappedStore = modelMapper.map(storeRepository.save(store), StoreVO.class);
        return Optional.of(mappedStore);
    }

    @Override
    public Optional<Long> delete(Long id) {
        storeRepository.deleteById(id);
        return Optional.of(id);
    }
}
