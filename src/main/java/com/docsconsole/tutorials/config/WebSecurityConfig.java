package com.docsconsole.tutorials.config;

import com.docsconsole.tutorials.AppBasicAuthenticationEntryPoint;
import com.docsconsole.tutorials.CustomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private AppBasicAuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/hello").permitAll()
				.anyRequest().authenticated()
			)
			.httpBasic().authenticationEntryPoint(authenticationEntryPoint);
		return http.build();
	}

	/*@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("user1")
				.password(passwordEncoder.encode("user1Pass"))
				.authorities("ROLE_USER");
	}*/
	/*@Bean
	AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
		return builder
				.inMemoryAuthentication()
				.withUser("user1")
				.password(passwordEncoder().encode("user1Pass"))
				.authorities("ROLE_USER").and().and().build();
	}*/
	/*@Bean
	AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
		return builder.userDetailsService(userDetailsService()).and().build();
	}*/
	/*@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user1")
				.password(passwordEncoder().encode("user1Pass"))
				//.roles("ROLE_USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}*/
	@Bean
	public UserDetailsService userDetailsService()
	{
		//User Role
		UserDetails theUser = User.withUsername("user1")
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
				.password("user1Pass").roles("USER").build();
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		userDetailsManager.createUser(theUser);
		return userDetailsManager;
	}
	/*@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(8);
	}*/


}
