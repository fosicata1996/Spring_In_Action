package fosi.taco_cloud.entity.tacos;

import lombok.Data;

import java.util.List;

@Data
public class Taco
{
    private String name;

    private List<Ingredient> ingredients;
}
