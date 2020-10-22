package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admins/**").hasAuthority("ADMIN")
                .antMatchers("/user/register/{knuffelId}").permitAll()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/user/payment").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/GamePage/{knuffelId}").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/VideoPage/{knuffelId}").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/user/**").hasAnyAuthority("USER","ADMIN")
                .anyRequest().permitAll();
        http.formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/user/appHome", true);
        http.logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/");
        http.csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password,true from user where username = ?")
                .authoritiesByUsernameQuery("select username, role from user where username = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
