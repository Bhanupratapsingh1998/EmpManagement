package com.empManagement.config;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.empManagement.security.CustomAuthAccessEntryPoint;
import com.empManagement.security.jwtAuthFilter;
import com.empManagement.serviceImpl.EmployeeServiceImpl;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private EmployeeServiceImpl serviceImpl;

	@Autowired
	private jwtAuthFilter jFilter;

	@Autowired
	private CustomAuthAccessEntryPoint entryPoint;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(serviceImpl);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/api/v1/addEmployee", "/api/v1/signin").permitAll()
				.anyRequest().authenticated().and().httpBasic().authenticationEntryPoint(entryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jFilter, UsernamePasswordAuthenticationFilter.class);
	}

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {

	}

	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return
	 * NoOpPasswordEncoder.getInstance(); }
	 */

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}