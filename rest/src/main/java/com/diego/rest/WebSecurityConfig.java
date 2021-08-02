package com.diego.rest;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig
    extends WebSecurityConfigurerAdapter  implements ApplicationContextAware {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/*").hasAnyRole("USER")
				.anyRequest().authenticated()
				.and().formLogin().permitAll()
				.and().logout().permitAll();

		http.csrf().disable();
	}

    protected void registerAuthentication(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder
            .inMemoryAuthentication()
                .withUser("diego").password("diego").roles("USER");
    }
    
}