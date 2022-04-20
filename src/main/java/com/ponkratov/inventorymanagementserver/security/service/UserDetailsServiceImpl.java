package com.ponkratov.inventorymanagementserver.security.service;

import com.ponkratov.inventorymanagementserver.model.Responsible;
import com.ponkratov.inventorymanagementserver.repository.ResponsibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    ResponsibleRepository responsibleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Responsible responsible = responsibleRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Пользователь " + username + " не найден"));
        return UserDetailsImpl.build(responsible);
    }
}
