package fosi.taco_cloud.configuration.security;

import fosi.taco_cloud.repository.security.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig
{
    public static final String USER = "ROLE_USER";

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository)
    {
        return username ->
            Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User '" + username + "' not found");
                });
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        return http
            .authorizeRequests()
            .antMatchers("/design", "/orders").hasRole("USER") //.access("hasRole('USER')")
            .antMatchers("/", "/**").permitAll() //.access("permitAll()")
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/design")
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .and()
            .build();

        /*
         * .access("hasRole('USER') &&
         * T(java.util.Calendar).getInstance().get("T(java.util.Calendar).DAY_OF_WEEK) == T(java.util.Calendar).TUESDAY")
         */
    }
}
