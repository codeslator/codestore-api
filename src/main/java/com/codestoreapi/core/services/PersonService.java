package com.codestoreapi.core.services;

import com.codestoreapi.vo.PersonVO;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Optional<List<PersonVO>> getAll();
    Optional<PersonVO> getById(Long id);
    Optional<PersonVO> save(PersonVO personVO);
    Optional<PersonVO> update(PersonVO personVO);
    Optional<Long> delete(Long id);
}
