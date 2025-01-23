package com.xplug.tech.usermanager;


import com.xplug.tech.commons.security.jwt.JwtProvider;
import com.xplug.tech.commons.security.jwt.JwtResponse;
import com.xplug.tech.exception.InvalidRequestException;
import com.xplug.tech.pojo.ResponseMessage;
import com.xplug.tech.usermanager.user.LoginForm;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@Tag(name = "Authentication Controller", description = "Auth Entry Point")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/auth/token")
public class AuthenticationController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final UserAccountDao userAccountDao;

    @Autowired
    private final PermissionDao roleRepository;

    @Autowired
    private final PasswordEncoder encoder;

    @Autowired
    private final JwtProvider jwtProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, UserAccountDao userAccountDao, PermissionDao roleRepository, PasswordEncoder encoder, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userAccountDao = userAccountDao;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/signin")

    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        log.info("authenticateUser() request: {} ", loginRequest.getUsername());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UserAccount userAccount = userAccountDao.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new InvalidRequestException("UserAccount not found"));

            String jwt = jwtProvider.generateJwtToken(authentication, userDetails.getAuthorities());

            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userAccount));
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

}
