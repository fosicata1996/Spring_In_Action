package fosi.taco_cloud.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Ingredient implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final String id;
    private final String name;
    private final Type type;

    public enum Type
    {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
