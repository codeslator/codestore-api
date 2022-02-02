package com.codestoreapi.web.controllers;

import com.codestoreapi.core.services.CountryService;
import com.codestoreapi.vo.CountryVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/countries")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Tag(name = "Countries Module Controller", description = "Countries module methods")
public class CountryController implements Serializable {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public @ResponseBody ResponseEntity<List<CountryVO>> getAll() {
        return new ResponseEntity(countryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<CountryVO> get(@PathVariable Long id) {
        return new ResponseEntity(countryService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public @ResponseBody ResponseEntity<CountryVO> save(@Validated @RequestBody CountryVO countryVO) {
        return new ResponseEntity(countryService.save(countryVO), HttpStatus.OK);
    }

    @PutMapping("/update")
    public @ResponseBody ResponseEntity<CountryVO> update(@Validated @RequestBody CountryVO countryVO) {
        return new ResponseEntity(countryService.update(countryVO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(countryService.delete(id), HttpStatus.OK);
    }
}
