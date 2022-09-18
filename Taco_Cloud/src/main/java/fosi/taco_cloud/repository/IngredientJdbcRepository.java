package fosi.taco_cloud.repository;

import fosi.taco_cloud.entity.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientJdbcRepository implements IngredientRepository
{

    private final JdbcTemplate jdbcTemplate;

    public IngredientJdbcRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ingredient> findAll()
    {
        return jdbcTemplate.query(
            "select id, name, type from Ingredient",
            IngredientJdbcRepository::mapRowToIngredient);
    }

    @Override
    public Optional<Ingredient> findById(String id)
    {
        List<Ingredient> results = jdbcTemplate.query(
            "select id, name, type from Ingredient where id=?",
            IngredientJdbcRepository::mapRowToIngredient,
            id);

        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient)
    {
        jdbcTemplate.update(
            "insert into Ingredient (id, name, type) values (?, ?, ?)",
            ingredient.getId(), ingredient.getName(), ingredient.getType().toString());

        return ingredient;
    }

    private static Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException
    {
        return new Ingredient(
            row.getString("id"),
            row.getString("name"),
            Ingredient.Type.valueOf(row.getString("type")));
    }
}
