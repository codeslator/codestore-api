package com.codestoreapi.core.services;

import com.codestoreapi.core.models.Category;
import com.codestoreapi.vo.CategoryVO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<List<CategoryVO>> getAll();
    Optional<CategoryVO> getById(Long id);
    Optional<CategoryVO> save(CategoryVO categoryVO);
    Optional<CategoryVO> update(CategoryVO categoryVO);
    Optional<Long> delete(Long id);
}
