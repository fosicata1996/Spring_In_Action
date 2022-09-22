package fosi.taco_cloud.repository.security;

import fosi.taco_cloud.entity.security.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
