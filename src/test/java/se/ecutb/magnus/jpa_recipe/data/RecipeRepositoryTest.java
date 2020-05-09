package se.ecutb.magnus.jpa_recipe.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.ecutb.magnus.jpa_recipe.model.*;
import se.ecutb.magnus.jpa_recipe.model.entity.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private TestEntityManager em;

    private Recipe testRecipe1;
    private Recipe testRecipe2;
    private Recipe testRecipe3;

    @BeforeEach
    void setUp() {
        Ingredient testIngredient1 = new Ingredient("TestIngredientOne");
        Ingredient testIngredient2 = new Ingredient("TestIngredientTwo");

        RecipeIngredient testRecipeIngredient1 = new RecipeIngredient(testIngredient1, 0.5, Measurement.KG);
        RecipeIngredient testRecipeIngredient2 = new RecipeIngredient(testIngredient2, 2, Measurement.DL);
        RecipeIngredient testRecipeIngredient3 = new RecipeIngredient(testIngredient2, 1, Measurement.DL);

        RecipeInstruction testInstruction1= new RecipeInstruction("TestInstructionOne");
        RecipeInstruction testInstruction2= new RecipeInstruction("TestInstructionTwo");
        RecipeInstruction testInstruction3= new RecipeInstruction("TestInstructionThree");

        RecipeCategory testRecipeCategory1 = new RecipeCategory("TestRecipeCategoryOne");
        RecipeCategory testRecipeCategory2 = new RecipeCategory("TestRecipeCategoryTwo");

        testRecipe1 = recipeRepository.save(new Recipe("TestRecipeOne", testInstruction1));
        testRecipe2 = recipeRepository.save(new Recipe("TestRecipeTwo",testInstruction2));
        testRecipe3 = recipeRepository.save(new Recipe("TestRecipeThree",testInstruction3));

        testRecipe1.addRecipeIngredient(testRecipeIngredient1);
        testRecipe2.addRecipeIngredient(testRecipeIngredient2);
        testRecipe3.addRecipeIngredient(testRecipeIngredient3);

        testRecipe1.addRecipeCategory(testRecipeCategory1);
        testRecipe2.addRecipeCategory(testRecipeCategory2);
        testRecipe3.addRecipeCategory(testRecipeCategory2);

        em.flush();
    }

    @Test
    public void findRecipesByRecipeNameContains_test(){
        List<Recipe> result = recipeRepository.findRecipesByRecipeNameContains("Two");

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void findRecipeByIngredientName_test(){
        List<Recipe> result= recipeRepository.findRecipeByIngredientName("TestIngredientTwo");

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void findRecipeByCategory_test(){
        List<Recipe> result = recipeRepository.findRecipeByCategory("TestRecipeCategoryTwo");

        assertNotNull(result);
        assertEquals(2,result.size());
    }

    @Test
    public void findRecipeByCategories_test(){
        List<Recipe> result = recipeRepository.findRecipeByCategories(Arrays.asList("TestRecipeCategoryOne","TestRecipeCategoryTwo"));

        assertNotNull(result);
        assertEquals(3,result.size());
    }
}
