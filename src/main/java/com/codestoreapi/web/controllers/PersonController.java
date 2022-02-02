package com.codestoreapi.web.controllers;

import com.codestoreapi.core.services.PersonService;
import com.codestoreapi.vo.PersonVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/persons")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Tag(name = "Persons Module Controller", description = "Persons module methods")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public @ResponseBody ResponseEntity<List<PersonVO>> getAll() {
        return new ResponseEntity(personService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<PersonVO> get(@PathVariable("id") Long id) {
        return new ResponseEntity(personService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public @ResponseBody ResponseEntity<PersonVO> save(@Validated @RequestBody PersonVO person) {
        return new ResponseEntity(personService.save(person), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public @ResponseBody ResponseEntity<PersonVO> update(@Validated @RequestBody PersonVO person) {
        return new ResponseEntity(personService.update(person), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(personService.delete(id), HttpStatus.OK);
    }
}
