package org.product.webserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;

/**
 *
 */


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf()
				.disable()
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.httpBasic();

		http.sessionManagement()
				.sessionAuthenticationStrategy(new SessionFixationProtectionStrategy())
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
				.maximumSessions(2)
				.maxSessionsPreventsLogin(true)
				.expiredUrl("/session-expired.html");

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// {noop} => set the password encoder , in this case NoOpPasswordEncoder
		auth.inMemoryAuthentication()
				.withUser("user")
				.password("{noop}password")
				.roles("USER");

		auth.inMemoryAuthentication()
				.withUser("guest")
				.password("{noop}password")
				.roles("GUEST");
	}

}