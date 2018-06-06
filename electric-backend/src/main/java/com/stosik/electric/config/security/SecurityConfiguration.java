package com.stosik.electric.config.security;

import com.stosik.electric.config.security.utility.SecurityUtility;
import com.stosik.electric.service.impl.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    private final UserSecurityService userSecurityService;
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return SecurityUtility.passwordEncoder();
    }
    
    private static final String[] PUBLIC_MATCHERS = {
        "/webjars/**",
        "/css/**",
        "/js/**",
        "/images/**",
        "/",
        "/about/**",
        "/error/**/*",
        "/contact/**",
        "/signup",
        "/console/**",
        "/forgot-password/**",
        "/reset-password/**"
    };
    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
            .antMatchers(PUBLIC_MATCHERS)
            .permitAll()
            .anyRequest()
            .authenticated();
        
        http.csrf()
            .disable()
            .cors()
            .disable()
            .formLogin().failureUrl("/index?error").defaultSuccessUrl("/userFront").loginPage("/index").permitAll()
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index?logout").deleteCookies("remember-me").permitAll()
            .and()
            .rememberMe();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userSecurityService).passwordEncoder(SecurityUtility.passwordEncoder());
    }
}
