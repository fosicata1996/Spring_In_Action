package fosi.taco_cloud.repository;

import fosi.taco_cloud.entity.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long>
{
}
