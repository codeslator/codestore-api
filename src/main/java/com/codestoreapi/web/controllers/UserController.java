package com.codestoreapi.web.controllers;

import com.codestoreapi.core.models.User;
import com.codestoreapi.core.services.UserService;
import com.codestoreapi.vo.UserVO;
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
@RequestMapping("/v1/users")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Tag(name = "Users Module Controller", description = "Users module methods")
public class UserController implements Serializable {

    @Autowired
    private UserService userService;

    @GetMapping
    public @ResponseBody ResponseEntity<List<UserVO>> getAll() {
        return new ResponseEntity(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<UserVO> get(@PathVariable("id") Long id) {
        return new ResponseEntity(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public @ResponseBody ResponseEntity<UserVO> save(@Validated @RequestBody UserVO user) {
        return new ResponseEntity(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public @ResponseBody ResponseEntity<UserVO> update(@Validated @RequestBody UserVO user) {
        return new ResponseEntity(userService.update(user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(userService.delete(id), HttpStatus.OK);
    }
}
