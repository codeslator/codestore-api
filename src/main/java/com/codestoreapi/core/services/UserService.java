package com.codestoreapi.core.services;

import com.codestoreapi.core.models.User;
import com.codestoreapi.vo.UserVO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<List<UserVO>> getAll();
    Optional<UserVO> getById(Long id);
    Optional<UserVO> save(UserVO userVO);
    Optional<UserVO> update(UserVO userVO);
    Optional<Long> delete(Long id);
    Optional<UserVO> getByUsername(String username);
    Optional<UserVO> getByEmail(String email);
    Optional<UserVO> getByUsernameAndPassword(String username, String password);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    void changePassword(Long id, String password);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
