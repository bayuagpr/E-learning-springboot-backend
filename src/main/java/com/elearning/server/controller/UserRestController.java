package com.elearning.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.server.security.JwtTokenUtil;
import com.elearning.server.security.JwtUser;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/current")
    public ResponseEntity<JwtUser> getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String tokenReal = token.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(tokenReal);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok().body(user);
    }

}
