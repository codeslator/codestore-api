package com.codestoreapi.core.services.impl;

import com.codestoreapi.core.models.Category;
import com.codestoreapi.core.repositories.CategoryRepository;
import com.codestoreapi.core.services.CategoryService;
import com.codestoreapi.vo.CategoryVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<List<CategoryVO>> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryVO> mappedCategories = new ArrayList<>();
        categories.forEach(category -> mappedCategories.add(modelMapper.map(category, CategoryVO.class)));
        return Optional.of(mappedCategories);
    }

    @Override
    public Optional<CategoryVO> getById(Long id) {
        CategoryVO mappedCategory = modelMapper.map(categoryRepository.findById(id).get(), CategoryVO.class);
        return Optional.of(mappedCategory);
    }

    @Override
    public Optional<CategoryVO> save(CategoryVO categoryVO) {
        Category category = modelMapper.map(categoryVO, Category.class);
        CategoryVO mappedCategory = modelMapper.map(categoryRepository.save(category), CategoryVO.class);
        return Optional.of(mappedCategory);
    }

    @Override
    public Optional<CategoryVO> update(CategoryVO categoryVO) {
        Category category = modelMapper.map(categoryVO, Category.class);
        CategoryVO mappedCategory = modelMapper.map(categoryRepository.save(category), CategoryVO.class);
        return Optional.of(mappedCategory);
    }

    @Override
    public Optional<Long> delete(Long id) {
        categoryRepository.deleteById(id);
        return Optional.of(id);
    }
}
