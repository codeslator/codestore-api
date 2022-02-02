package com.codestoreapi.core.services.impl;

import com.codestoreapi.core.models.Role;
import com.codestoreapi.core.repositories.RoleRepository;
import com.codestoreapi.core.services.RoleService;
import com.codestoreapi.vo.RoleVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<List<RoleVO>> getAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleVO> mappedRoles = new ArrayList<>();
        roles.forEach(role -> mappedRoles.add(modelMapper.map(role, RoleVO.class)));
        return Optional.of(mappedRoles);
    }

    @Override
    public Optional<RoleVO> getById(Long id) {
        RoleVO mapperRole = modelMapper.map(roleRepository.findById(id).get(), RoleVO.class);
        return Optional.of(mapperRole);
    }

    @Override
    public Optional<RoleVO> save(RoleVO roleVO) {
        Role role = modelMapper.map(roleVO, Role.class);
        RoleVO mappedRole = modelMapper.map(roleRepository.save(role), RoleVO.class);
        return Optional.of(mappedRole);
    }

    @Override
    public Optional<RoleVO> update(RoleVO roleVO) {
        Role role = modelMapper.map(roleVO, Role.class);
        RoleVO mappedRole = modelMapper.map(roleRepository.save(role), RoleVO.class);
        return Optional.of(mappedRole);
    }

    @Override
    public Optional<Long> delete(Long id) {
        roleRepository.deleteById(id);
        return Optional.of(id);
    }

    @Override
    public Optional<RoleVO> findByName(String name) {
        RoleVO mapperRole = modelMapper.map(roleRepository.findByName(name).get(), RoleVO.class);
        return Optional.of(mapperRole);
    }
}
