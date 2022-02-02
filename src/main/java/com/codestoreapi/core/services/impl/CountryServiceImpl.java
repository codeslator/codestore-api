package com.codestoreapi.core.services.impl;

import com.codestoreapi.core.models.Country;
import com.codestoreapi.core.repositories.CountryRepository;
import com.codestoreapi.core.services.CountryService;
import com.codestoreapi.vo.CountryVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Optional<List<CountryVO>> getAll() {
        List<Country> countries = countryRepository.findAll();
        List<CountryVO> mappedCountries = new ArrayList<>();
        countries.forEach(country -> mappedCountries.add(modelMapper.map(country, CountryVO.class)));
        return Optional.of(mappedCountries);
    }

    @Override
    public Optional<CountryVO> getById(Long id) {
        CountryVO mappedCountry = modelMapper.map(countryRepository.findById(id).get(), CountryVO.class);
        return Optional.of(mappedCountry);
    }

    @Override
    public Optional<CountryVO> save(CountryVO countryVO) {
        Country country = modelMapper.map(countryVO, Country.class);
        CountryVO mappedCountry = modelMapper.map(countryRepository.save(country), CountryVO.class);
        return Optional.of(mappedCountry);
    }

    @Override
    public Optional<CountryVO> update(CountryVO countryVO) {
        Country country = modelMapper.map(countryVO, Country.class);
        CountryVO mappedCountry = modelMapper.map(countryRepository.save(country), CountryVO.class);
        return Optional.of(mappedCountry);
    }

    @Override
    public Optional<Long> delete(Long id) {
        countryRepository.deleteById(id);
        return Optional.of(id);
    }
}
