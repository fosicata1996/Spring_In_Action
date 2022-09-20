package fosi.taco_cloud.repository;

import fosi.taco_cloud.entity.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID>
{
}
