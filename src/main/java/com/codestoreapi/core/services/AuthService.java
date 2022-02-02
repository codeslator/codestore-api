package com.codestoreapi.core.services;

import com.codestoreapi.vo.AuthVO;
import com.codestoreapi.vo.SignInVO;
import com.codestoreapi.vo.SignUpVO;
import com.codestoreapi.vo.UserVO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface AuthService {

    Optional<UserVO> signUp(SignUpVO signUpVO);
    Optional<AuthVO> signIn(SignInVO signInVO);
    void signOut();
//    void save(User user);
//    public void activateAccount(String email);
//    public void updateVerificationCode(Long id, String code);
}
