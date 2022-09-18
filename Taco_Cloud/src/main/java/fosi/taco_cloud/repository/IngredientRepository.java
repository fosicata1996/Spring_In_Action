package fosi.taco_cloud.repository;

import fosi.taco_cloud.entity.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository
{
    List<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
