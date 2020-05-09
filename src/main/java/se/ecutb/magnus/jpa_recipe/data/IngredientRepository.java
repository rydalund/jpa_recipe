package se.ecutb.magnus.jpa_recipe.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.ecutb.magnus.jpa_recipe.model.entity.Ingredient;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    Optional<Ingredient> findByIngredientName(String ingredientName);
    List<Ingredient> findByIngredientNameContains(String ingredientName);
}