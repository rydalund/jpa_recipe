package se.ecutb.magnus.jpa_recipe.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.ecutb.magnus.jpa_recipe.model.entity.Recipe;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    @Query("SELECT recipe from Recipe recipe WHERE recipe.recipeName LIKE %:recipeName%")
    List<Recipe> findRecipesByRecipeNameContains(@Param("recipeName") String recipeName);

    @Query("SELECT recipe FROM Recipe recipe JOIN FETCH recipe.recipeIngredients recipeIngredient WHERE recipeIngredient.ingredient.ingredientName = :ingredientName")
    List<Recipe> findRecipeByIngredientName(@Param("ingredientName") String ingredientName);

    @Query("SELECT recipe FROM Recipe recipe JOIN FETCH recipe.categories recipeCategories WHERE recipeCategories.category = :category")
    List<Recipe> findRecipeByCategory(@Param("category") String category);

    @Query("SELECT recipe FROM Recipe recipe JOIN FETCH recipe.categories recipeCategories WHERE recipeCategories.category IN :categories")
    List<Recipe> findRecipeByCategories(@Param("categories")List<String> categories);
}
