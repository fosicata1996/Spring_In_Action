package fosi.taco_cloud.configuration.security;

import fosi.taco_cloud.repository.security.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
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
}
