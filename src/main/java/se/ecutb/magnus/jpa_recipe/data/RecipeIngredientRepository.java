package se.ecutb.magnus.jpa_recipe.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.ecutb.magnus.jpa_recipe.model.entity.RecipeIngredient;

@Repository
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, String> {
}