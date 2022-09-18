package fosi.taco_cloud.entity.web;

import fosi.taco_cloud.entity.Ingredient;
import fosi.taco_cloud.repository.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient>
{
    private final IngredientRepository ingredientRepository;

    public IngredientByIdConverter(IngredientRepository ingredientRepository)
    {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id)
    {
        return ingredientRepository.findById(id).orElse(null);
    }
}
