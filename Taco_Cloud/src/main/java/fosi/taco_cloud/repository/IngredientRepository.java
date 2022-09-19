package fosi.taco_cloud.repository;

import fosi.taco_cloud.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String>
{
}
