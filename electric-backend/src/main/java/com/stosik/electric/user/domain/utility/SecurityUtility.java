package com.stosik.electric.user.domain.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Random;

public class SecurityUtility
{
    
    private static final String SALT = "salt"; //Salt should be protected carefully
    
    public static BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }
    
    @Bean
    public static String randomPassword()
    {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        
        while(salt.length() < 18)
        {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        
        return salt.toString();
    }
}
