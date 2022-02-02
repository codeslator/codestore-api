package com.codestoreapi.core.services.impl;

import com.codestoreapi.core.models.User;
import com.codestoreapi.core.repositories.UserRepository;
import com.codestoreapi.core.services.UserService;
import com.codestoreapi.vo.UserVO;
import com.codestoreapi.web.beans.AuthDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<List<UserVO>> getAll() {
        List<User> users = userRepository.findAll();
        List<UserVO> mappedUsers = new ArrayList<>();
        users.forEach(user -> mappedUsers.add(modelMapper.map(user, UserVO.class)));
        return Optional.of(mappedUsers);
    }

    @Override
    public Optional<UserVO> getById(Long id) {
        UserVO mappedUser = modelMapper.map(userRepository.findById(id).get(), UserVO.class);
        return Optional.of(mappedUser);
    }

    @Override
    public Optional<UserVO> save(UserVO userVO) {
        User user = modelMapper.map(userVO, User.class);
        UserVO mappedUser = modelMapper.map(userRepository.save(user), UserVO.class);
        return Optional.of(mappedUser);
    }

    @Override
    public Optional<UserVO> update(UserVO userVO) {
        User user = modelMapper.map(userVO, User.class);
        UserVO mappedUser = modelMapper.map(userRepository.save(user), UserVO.class);
        return Optional.of(mappedUser);
    }

    @Override
    public Optional<Long> delete(Long id) {
        userRepository.deleteById(id);
        return Optional.of(id);
    }

    @Override
    public Optional<UserVO> getByUsername(String username) {
        UserVO mappedUser = modelMapper.map(userRepository.findByUsername(username).get(), UserVO.class);
        return Optional.of(mappedUser);
    }

    @Override
    public Optional<UserVO> getByEmail(String email) {
        UserVO mappedUser = modelMapper.map(userRepository.findByEmail(email).get(), UserVO.class);
        return Optional.of(mappedUser);
    }

    @Override
    public Optional<UserVO> getByUsernameAndPassword(String username, String password) {
        UserVO mappedUser = modelMapper.map(userRepository.findByUsernameAndPassword(username, password).get(), UserVO.class);
        return Optional.of(mappedUser);
    }

    @Override
    public Boolean existsByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }

    @Override
    public Boolean existsByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public void changePassword(Long id, String password) {
        userRepository.updatePassword(id, password);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return AuthDetails.build(user);
    }
}
