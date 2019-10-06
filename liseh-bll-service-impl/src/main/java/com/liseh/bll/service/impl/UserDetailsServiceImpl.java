package com.liseh.bll.service.impl;

import com.liseh.bll.event.AppEventManager;
import com.liseh.bll.persistence.repository.UserRepository;
import com.liseh.bll.exception.BaseException;
import com.liseh.bll.persistence.entity.Role;
import com.liseh.bll.persistence.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        AppEventManager.register(this.getClass(), "TEST_CUSTOM_EVENT", () -> System.out.println("FROM: UserDetailsServiceImpl"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new BaseException("No user exists by " + email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword().toLowerCase(),
                true,
                true,
                true,
                true,
                getAuthorities(user.getRoles()));
    }

    private static List<GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
