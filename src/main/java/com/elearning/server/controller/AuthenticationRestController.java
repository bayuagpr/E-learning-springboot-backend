package com.elearning.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

import com.elearning.server.model.User;
import com.elearning.server.security.JwtAuthenticationRequest;
import com.elearning.server.security.JwtTokenUtil;
import com.elearning.server.security.JwtUser;
import com.elearning.server.service.JwtAuthenticationResponse;
import com.elearning.server.service.RegistrationService;
import com.elearning.server.validator.EmailValidator;
import com.elearning.server.validator.PasswordValidator;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/v1/credential")
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

   
    private RegistrationService registrationService;

 
    private EmailValidator emailValidator;

 
    private PasswordValidator passwordValidator;


    private AuthenticationManager authenticationManager;

 
    private JwtTokenUtil jwtTokenUtil;


    private UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationRestController(RegistrationService registrationService, EmailValidator emailValidator, 
    PasswordValidator passwordValidator, AuthenticationManager authenticationManager,
    JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {
        this.registrationService = registrationService;
        this.authenticationManager = authenticationManager;
        this.emailValidator = emailValidator;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordValidator = passwordValidator;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/registration")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody User user){
        
        log.info(user.getPassword());
        if (!emailValidator.isValid(user.getEmail())) {
            return ResponseEntity.badRequest().body("email tidak valid");
        }
        if (!passwordValidator.isValid(user.getPassword())) {
            return ResponseEntity.badRequest().body("password tidak valid");
        }



        registrationService.simpanUser(user);


        JwtAuthenticationRequest newAuth = new JwtAuthenticationRequest();
        newAuth.setEmail(user.getEmail());
        newAuth.setPassword(user.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(newAuth.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    
    

}
