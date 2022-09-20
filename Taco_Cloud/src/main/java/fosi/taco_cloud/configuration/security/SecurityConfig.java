package fosi.taco_cloud.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configuration
public class SecurityConfig
{
    private static final String USER = "ROLE_USER";

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder)
    {
        return new InMemoryUserDetailsManager(List.of(
            new User("buzz", encoder.encode("buzz"), List.of(new SimpleGrantedAuthority(USER))),
            new User("woody", encoder.encode("woody"), List.of(new SimpleGrantedAuthority(USER)))
        ));
    }
}
