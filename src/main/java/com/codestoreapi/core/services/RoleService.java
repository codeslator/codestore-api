package com.codestoreapi.core.services;

import com.codestoreapi.core.models.Role;
import com.codestoreapi.vo.RoleVO;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<List<RoleVO>> getAll();
    Optional<RoleVO> getById(Long id);
    Optional<RoleVO> save(RoleVO roleVO);
    Optional<RoleVO> update(RoleVO roleVO);
    Optional<Long> delete(Long id);
    Optional<RoleVO> findByName(String name);
}
