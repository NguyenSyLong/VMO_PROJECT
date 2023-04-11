package com.apartment.vmoproject.api.service.impl;

import com.apartment.vmoproject.api.model.CustomUserDetail;
import com.apartment.vmoproject.api.model.Account;
import com.apartment.vmoproject.api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetail(user.get());
    }


}
