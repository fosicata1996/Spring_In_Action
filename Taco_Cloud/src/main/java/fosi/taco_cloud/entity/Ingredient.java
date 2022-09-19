package fosi.taco_cloud.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@Table
public class Ingredient implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    private final String id;
    private final String name;
    private final Type type;

    public enum Type
    {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
