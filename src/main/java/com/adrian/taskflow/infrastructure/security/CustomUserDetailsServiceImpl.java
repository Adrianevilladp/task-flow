package com.adrian.taskflow.infrastructure.security;

import com.adrian.taskflow.domain.model.User;
import com.adrian.taskflow.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService, CustomUserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =
                 userRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () -> new UsernameNotFoundException(
                                        String.format(
                                                "User not found with this username or email: %s", email)));
        return UserPrincipal.create(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        User user =
                userRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new UsernameNotFoundException(String.format("User not found with id: %s", id)));
        return UserPrincipal.create(user);
    }
}
