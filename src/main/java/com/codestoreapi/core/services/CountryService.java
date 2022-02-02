package com.codestoreapi.core.services;

import com.codestoreapi.vo.CountryVO;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<List<CountryVO>> getAll();
    Optional<CountryVO> getById(Long id);
    Optional<CountryVO> save(CountryVO countryVO);
    Optional<CountryVO> update(CountryVO countryVO);
    Optional<Long> delete(Long id);
}
