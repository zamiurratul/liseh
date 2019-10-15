package com.liseh.bll.auth.config;

import com.liseh.bll.auth.service.impl.UserDetailsServiceImpl;
import com.liseh.bll.constant.RoleType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AppSecurityConfig(UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setAuthoritiesMapper(grantedAuthoritiesMapper());
        return authenticationProvider;
    }

    @Bean
    public GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setConvertToUpperCase(true);
        authorityMapper.setDefaultAuthority(RoleType.USER);
        return authorityMapper;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/token/*",
                        "/v2/api-docs",           // swagger
                        "/webjars/**",            // swagger-ui webjars
                        "/swagger-resources/**",  // swagger-ui resources
                        "/configuration/**",      // swagger configuration
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/ws/**",
                        "/**/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        .authenticationEntryPoint(basicAuthenticationEntryPoint);
//        httpSecurity.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
    }
}
