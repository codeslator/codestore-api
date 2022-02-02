package com.codestoreapi.web.controllers;

import com.codestoreapi.core.services.RoleService;
import com.codestoreapi.vo.RoleVO;
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
@RequestMapping("/v1/roles")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Tag(name = "Roles Module Controller", description = "Roles module methods")
public class RoleController implements Serializable {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public @ResponseBody ResponseEntity<List<RoleVO>> getAll() {
        return new ResponseEntity(roleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<RoleVO> get(@PathVariable("id") Long id) {
        return new ResponseEntity(roleService.getById(id), HttpStatus.OK);
    }


    @PostMapping("/save")
    public @ResponseBody ResponseEntity<RoleVO> save(@Validated @RequestBody RoleVO roleVO) {
        return new ResponseEntity(roleService.save(roleVO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public @ResponseBody ResponseEntity<RoleVO> update(@Validated @RequestBody RoleVO roleVO) {
        return new ResponseEntity(roleService.update(roleVO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(roleService.delete(id), HttpStatus.OK);
    }
}
