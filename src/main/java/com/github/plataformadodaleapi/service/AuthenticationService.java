package com.github.plataformadodaleapi.service;


import com.github.plataformadodaleapi.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
    private RecruiterRepository recruiterRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return recruiterRepository.findByemail(username);
    }

    public AuthenticationService(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }
}