package com.codestoreapi.core.services.impl;

import com.codestoreapi.core.models.Person;
import com.codestoreapi.core.repositories.PersonRepository;
import com.codestoreapi.core.services.PersonService;
import com.codestoreapi.vo.PersonVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<List<PersonVO>> getAll() {
        List<Person> persons = personRepository.findAll();
        List<PersonVO> mappedPersons = new ArrayList<>();
        persons.forEach(person -> mappedPersons.add(modelMapper.map(person, PersonVO.class)));
        return Optional.of(mappedPersons);
    }

    @Override
    public Optional<PersonVO> getById(Long id) {
        PersonVO mappedPerson = modelMapper.map(personRepository.findById(id).get(), PersonVO.class);
        return Optional.of(mappedPerson);
    }

    @Override
    public Optional<PersonVO> save(PersonVO personVO) {
        Person person = modelMapper.map(personVO, Person.class);
        PersonVO mappedPerson = modelMapper.map(personRepository.save(person), PersonVO.class);
        return Optional.of(mappedPerson);
    }

    @Override
    public Optional<PersonVO> update(PersonVO personVO) {
        Person person = modelMapper.map(personVO, Person.class);
        PersonVO mappedPerson = modelMapper.map(personRepository.save(person), PersonVO.class);
        return Optional.of(mappedPerson);
    }

    @Override
    public Optional<Long> delete(Long id) {
        personRepository.deleteById(id);
        return Optional.of(id);
    }

    @Override
    public Boolean uploadImage(MultipartFile image) {
        return null;
    }
}
