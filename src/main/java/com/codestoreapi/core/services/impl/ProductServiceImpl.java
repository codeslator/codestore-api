package com.codestoreapi.core.services.impl;

import com.codestoreapi.core.models.Product;
import com.codestoreapi.core.repositories.ProductRepository;
import com.codestoreapi.core.services.ProductService;
import com.codestoreapi.vo.ProductVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<List<ProductVO>> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductVO> mappedProducts = new ArrayList<>();
        products.forEach(product -> mappedProducts.add(modelMapper.map(product, ProductVO.class)));
        return Optional.of(mappedProducts);
    }

    @Override
    public Optional<ProductVO> getById(Long id) {
        ProductVO mappedProduct = modelMapper.map(productRepository.findById(id).get(), ProductVO.class);
        return Optional.of(mappedProduct);
    }

    @Override
    public Optional<ProductVO> save(ProductVO productVO) {
        Product product = modelMapper.map(productVO, Product.class);
        ProductVO mappedProduct = modelMapper.map(productRepository.save(product), ProductVO.class);
        return Optional.of(mappedProduct);
    }

    @Override
    public Optional<ProductVO> update(ProductVO productVO) {
        Product product = modelMapper.map(productVO, Product.class);
        ProductVO mappedProduct = modelMapper.map(productRepository.save(product), ProductVO.class);
        return Optional.of(mappedProduct);
    }

    @Override
    public Optional<Long> delete(Long id) {
        productRepository.deleteById(id);
        return Optional.of(id);
    }
}
