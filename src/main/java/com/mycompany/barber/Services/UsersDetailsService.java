package com.mycompany.barber.Services;

import com.mycompany.barber.Models.User;
import com.mycompany.barber.Repository.UserRepository;
import com.mycompany.barber.Security.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

@Service
public class UsersDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UsersDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUserName(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UsersDetails(user.get());
    }
}
