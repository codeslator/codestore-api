package com.codestoreapi.core.services.impl;

import com.codestoreapi.core.services.*;
import com.codestoreapi.utils.enums.RoleEnum;
import com.codestoreapi.utils.enums.UserTypeEnum;
import com.codestoreapi.utils.jwt.JwtUtils;
import com.codestoreapi.vo.*;
import com.codestoreapi.web.beans.AuthDetails;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PersonService personService;

    @Autowired
    private StoreService storeService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public Optional<UserVO> signUp(SignUpVO signUpVO) {
        UserVO user = new UserVO(
                signUpVO.getUsername(),
                signUpVO.getEmail(),
                encoder.encode(signUpVO.getPassword()),
                signUpVO.getType(),
                signUpVO.getAuth2Factor(),
                signUpVO.getAuth2ExpireTime(),
                signUpVO.getAuth2DefaultType()
        );
        Set<String> strRoles = signUpVO.getRole();
        List<RoleVO> roles = new ArrayList<>();
        if (strRoles == null) {
            RoleVO userRole = roleService.findByName(RoleEnum.CUSTOMER.name())
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "CUSTOMER":
                        RoleVO customerRole = roleService.findByName(RoleEnum.CUSTOMER.name())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(customerRole);

                        break;
                    case "MANAGER":
                        RoleVO managerRole = roleService.findByName(RoleEnum.MANAGER.name())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(managerRole);

                        break;
                    case "STORE":
                        RoleVO storeRole = roleService.findByName(RoleEnum.STORE.name())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(storeRole);

                        break;
                    default:
                        RoleVO userRole = roleService.findByName(RoleEnum.USER.name())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        if(signUpVO.getType().equals(UserTypeEnum.PERSON.name())) {
            user.setPerson(modelMapper.map(signUpVO.getAuth(), PersonVO.class));
        }
        else if(signUpVO.getType().equals(UserTypeEnum.STORE.name())) {
            user.setStore(modelMapper.map(signUpVO.getAuth(), StoreVO.class));
        }
        user.setRoles(roles);
        UserVO mappedUser = userService.save(user).get();
        return Optional.of(mappedUser);
    }

    @Override
    public Optional<AuthVO> signIn(SignInVO signInVO) {
        PersonVO person = null;
        StoreVO store = null;
        Long personId = null;
        Long storeId = null;
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInVO.getUsername(), signInVO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        AuthDetails authDetails = (AuthDetails) authentication.getPrincipal();
        List<String> roles = authDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        if(authDetails.getType().equals(UserTypeEnum.PERSON.name())) {
            person = modelMapper.map(authDetails.getAuth(), PersonVO.class);
            personId = person.getId();
        }
        else if(authDetails.getType().equals(UserTypeEnum.STORE.name())) {
            store = modelMapper.map(authDetails.getAuth(), StoreVO.class);
            storeId = store.getId();
        }
        AuthVO auth = new AuthVO(
                authDetails.getId(),
                jwt,
                authDetails.getUsername(),
                authDetails.getEmail(),
                authDetails.getType(),
                storeId,
                store,
                personId,
                person,
                roles
        );
        return Optional.of(auth);
    }

    @Override
    public void signOut() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
