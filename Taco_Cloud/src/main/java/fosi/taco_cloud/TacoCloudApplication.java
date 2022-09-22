package fosi.taco_cloud;

import fosi.taco_cloud.repository.IngredientRepository;
import fosi.taco_cloud.repository.security.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Profile("!qa")
public class TacoCloudApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    @Bean
    //    @Profile("dev")
    //    @Profile({"dev", "qa"})
    @Profile("!prod")
    public CommandLineRunner dataLoader(IngredientRepository ingredientRepository, UserRepository userRepository,
        PasswordEncoder encoder)
    {
        return null;
    }

}
