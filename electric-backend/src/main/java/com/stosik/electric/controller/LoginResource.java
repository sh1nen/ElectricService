package com.stosik.electric.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@RestController
public class LoginResource
{
    @RequestMapping("/token")
    public Map<String, String> token(HttpSession session, HttpServletRequest request)
    {
        return Collections.singletonMap("token", session.getId());
    }
    
    @RequestMapping("/checkSession")
    public ResponseEntity checkSession()
    {
        return new ResponseEntity("Session Active!", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/logout", method = RequestMethod.POST)
    public ResponseEntity logout()
    {
        SecurityContextHolder.clearContext();
        return new ResponseEntity("Logout Successfully!", HttpStatus.OK);
    }
}
