package com.codestoreapi.core.services;

import com.codestoreapi.core.models.SubCategory;
import com.codestoreapi.vo.SubCategoryVO;

import java.util.List;
import java.util.Optional;

public interface SubCategoryService {
    Optional<List<SubCategoryVO>> getAll();
    Optional<SubCategoryVO> getById(Long id);
    Optional<SubCategoryVO> save(SubCategoryVO subCategoryVO);
    Optional<SubCategoryVO> update(SubCategoryVO subCategoryVO);
    Optional<Long> delete(Long id);
}
