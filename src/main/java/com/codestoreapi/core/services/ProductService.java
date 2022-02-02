package com.codestoreapi.core.services;

import com.codestoreapi.core.models.Product;
import com.codestoreapi.vo.ProductVO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<List<ProductVO>> getAll();
    Optional<ProductVO> getById(Long id);
    Optional<ProductVO> save(ProductVO productVO);
    Optional<ProductVO> update(ProductVO productVO);
    Optional<Long> delete(Long id);
}
