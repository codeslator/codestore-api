package com.codestoreapi.core.services.impl;

import com.codestoreapi.core.models.SubCategory;
import com.codestoreapi.core.repositories.SubCategoryRepository;
import com.codestoreapi.core.services.SubCategoryService;
import com.codestoreapi.vo.SubCategoryVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<List<SubCategoryVO>> getAll() {
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        List<SubCategoryVO> mappedSubCategories = new ArrayList<>();
        subCategories.forEach(subCategory -> mappedSubCategories.add(modelMapper.map(subCategory, SubCategoryVO.class)));
        return Optional.of(mappedSubCategories);
    }

    @Override
    public Optional<SubCategoryVO> getById(Long id) {
        SubCategoryVO mappedSubCategory = modelMapper.map(subCategoryRepository.findById(id).get(), SubCategoryVO.class);
        return Optional.of(mappedSubCategory);
    }

    @Override
    public Optional<SubCategoryVO> save(SubCategoryVO subCategoryVO) {
        SubCategory subCategory = modelMapper.map(subCategoryVO, SubCategory.class);
        SubCategoryVO mappedSubCategory = modelMapper.map(subCategoryRepository.save(subCategory), SubCategoryVO.class);
        return Optional.of(mappedSubCategory);
    }

    @Override
    public Optional<SubCategoryVO> update(SubCategoryVO subCategoryVO) {
        SubCategory subCategory = modelMapper.map(subCategoryVO, SubCategory.class);
        SubCategoryVO mappedSubCategory = modelMapper.map(subCategoryRepository.save(subCategory), SubCategoryVO.class);
        return Optional.of(mappedSubCategory);
    }

    @Override
    public Optional<Long> delete(Long id) {
        subCategoryRepository.deleteById(id);
        return Optional.of(id);
    }
}
