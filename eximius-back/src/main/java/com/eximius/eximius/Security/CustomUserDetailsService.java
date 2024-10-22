package com.eximius.eximius.Security;

import com.eximius.eximius.Entities.Role;
import com.eximius.eximius.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //Method to bring us a list of authorities by means of a list of roles
    public Collection<GrantedAuthority> mapToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    //Method to bring us a user with all his data by means of his userName
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.eximius.eximius.Entities.User users = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return new org.springframework.security.core.userdetails.User(
                users.getUserName(),
                users.getPassword(),
                mapToAuthorities(users.getRoles())
        );
    }

}
