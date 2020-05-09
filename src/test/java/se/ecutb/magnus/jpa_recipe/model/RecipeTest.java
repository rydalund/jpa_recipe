package se.ecutb.magnus.jpa_recipe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.ecutb.magnus.jpa_recipe.model.entity.Ingredient;
import se.ecutb.magnus.jpa_recipe.model.entity.Recipe;
import se.ecutb.magnus.jpa_recipe.model.entity.RecipeCategory;
import se.ecutb.magnus.jpa_recipe.model.entity.RecipeIngredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RecipeTest {
    private Recipe testRecipe1;
    private Recipe testRecipe2;
    private Recipe testRecipe3;

    private RecipeCategory testRecipeCategory1;
    private RecipeCategory testRecipeCategory2;
    private RecipeCategory testRecipeCategory3;

    private RecipeIngredient testRecipeIngredient1;
    private RecipeIngredient testRecipeIngredient2;
    private RecipeIngredient testRecipeIngredient3;

    @BeforeEach
    void setUp() {
        testRecipe1 = new Recipe("TestRecipeOne");
        testRecipe2 = new Recipe("TestRecipeTwo");
        //testRecipe3 = new Recipe("TestRecipeThree");

        testRecipeCategory1 = new RecipeCategory("TestRecipeCategoryOne");
        testRecipeCategory2 = new RecipeCategory("TestRecipeCategoryTwo");
        //testRecipeCategory3 = new RecipeCategory("TestRecipeCategoryThree");

        RecipeIngredient testRecipeIngredient1 = new RecipeIngredient(new Ingredient("TestIngredientOne"), 0.5, Measurement.KG);
        RecipeIngredient testRecipeIngredient2 = new RecipeIngredient(new Ingredient("TestIngredientTwo"), 2, Measurement.DL);
        //RecipeIngredient testRecipeIngredient3 = new RecipeIngredient(new Ingredient("TestIngredientThree"), 1, Measurement.DL);
    }

    @Test
    public void addRecipeIngredient_test(){
        testRecipe1.addRecipeIngredient(testRecipeIngredient1);
        testRecipe1.addRecipeIngredient(testRecipeIngredient2);

        assertNotNull(testRecipe1.getRecipeIngredients());
        assertEquals(2,testRecipe1.getRecipeIngredients().size());
    }

    @Test
    public void removeRecipeIngredient_test(){
        testRecipe2.addRecipeIngredient(testRecipeIngredient1);
        testRecipe2.addRecipeIngredient(testRecipeIngredient2);
        testRecipe2.removeRecipeIngredient(testRecipeIngredient2);

        assertNotNull(testRecipe2.getRecipeIngredients());
        assertEquals(1,testRecipe2.getRecipeIngredients().size());
    }

    @Test
    public void addRecipeCategory_test(){
        testRecipe1.addRecipeCategory(testRecipeCategory1);
        testRecipe1.addRecipeCategory(testRecipeCategory2);

        assertNotNull(testRecipe1.getCategories());
        assertEquals(2,testRecipe1.getCategories().size());
    }

    @Test
    public void removeRecipeCategory_test(){
        testRecipe2.addRecipeCategory(testRecipeCategory1);
        testRecipe2.addRecipeCategory(testRecipeCategory2);
        testRecipe2.removeRecipeCategory(testRecipeCategory2);

        assertNotNull(testRecipe2.getCategories());
        assertEquals(1,testRecipe2.getCategories().size());
    }

}
