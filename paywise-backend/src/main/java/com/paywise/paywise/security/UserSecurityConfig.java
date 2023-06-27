package com.paywise.paywise.security;

import com.paywise.paywise.constants.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class UserSecurityConfig {

  @Bean // Getting users from DB via jdbcc
  public UserDetailsManager userDetailsManager(DataSource dataSource){
    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

    jdbcUserDetailsManager.setUsersByUsernameQuery(
            "SELECT user_name, password, 1 FROM user WHERE user_name = ?"
    );

    // Getting roles from DB
    // Authorities == Roles (custom DB access)
    jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "SELECT user_name, CONCAT('ROLE_', role) FROM user INNER JOIN roles ON user.id = roles.user_id WHERE user_name = ?"
    );

    System.out.println(jdbcUserDetailsManager.getUsersByUsernameQuery());

    return jdbcUserDetailsManager;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
    httpSecurity.authorizeHttpRequests(configurer ->
            configurer
                    .requestMatchers(HttpMethod.GET, "/home/hello").permitAll()
                    .requestMatchers(HttpMethod.GET, "/home/users").permitAll() // TODO : hasRole(Constants.SIMPLE_USER) // SIMPLE_USER = "USER"
                    .requestMatchers(HttpMethod.GET, "/home/users/**").hasRole(Constants.SIMPLE_USER)
                    .requestMatchers(HttpMethod.POST, "/home/add").hasRole(Constants.SIMPLE_USER)
                    .requestMatchers(HttpMethod.POST, "/home/add").hasRole(Constants.ADMIN_USER)
                    .requestMatchers(HttpMethod.POST, "/home/add/**").hasRole(Constants.ADMIN_USER)
                    .requestMatchers(HttpMethod.DELETE, "/home/delete/**").hasRole(Constants.ADMIN_USER)
                    .requestMatchers(HttpMethod.PUT, "/home/update").hasRole(Constants.ADMIN_USER)
    );

    httpSecurity.httpBasic(Customizer.withDefaults());
    httpSecurity.csrf(AbstractHttpConfigurer::disable);

    return httpSecurity.build();
  }
}
