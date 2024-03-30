package com.github.plataformadodaleapi.controller;


import com.github.plataformadodaleapi.model.auth.EmailVerificationDTO;
import com.github.plataformadodaleapi.model.recruiter.AuthenticationRecruiterDTO;
import com.github.plataformadodaleapi.model.recruiter.RecruiterModel;
import com.github.plataformadodaleapi.repository.RecruiterRepository;
import com.github.plataformadodaleapi.security.TokenJWTDTO;
import com.github.plataformadodaleapi.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private RecruiterRepository recruiterRepository;
    private TokenService tokenService;

    @PostMapping("/verification")
    @Transactional
    public ResponseEntity<Boolean> authentication(@RequestBody EmailVerificationDTO dto) {
        String email = dto.email();
        boolean emailExists = recruiterRepository.existsByemail(email);
        return ResponseEntity.ok(emailExists);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenJWTDTO> login(@RequestBody AuthenticationRecruiterDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateToken((RecruiterModel) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
    }

    public AuthenticationController(AuthenticationManager authenticationManager, RecruiterRepository recruiterRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.recruiterRepository = recruiterRepository;
        this.tokenService = tokenService;
    }
}