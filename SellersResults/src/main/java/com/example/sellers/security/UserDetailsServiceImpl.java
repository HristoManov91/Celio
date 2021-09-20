package com.example.sellers.security;

import com.example.sellers.model.entity.UserEntity;
import com.example.sellers.service.UserRoleService;
import com.example.sellers.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String fullName) throws UsernameNotFoundException {
        UserEntity userEntity = userService.findUserByName(fullName)
                .orElseThrow(() -> new UsernameNotFoundException("User with this full name " + fullName + " was not found."));

        return mapToUserDetails(userEntity);
    }

    private UserDetails mapToUserDetails(UserEntity userEntity) {

        List<SimpleGrantedAuthority> authorities =
                userEntity
                        .getRoles()
                        .stream()
                        .map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.getRole().name())).collect(Collectors.toList());

        return new User(userEntity.getFullName(), userEntity.getPassword(), authorities);
    }
}
