package se.ecutb.magnus.jpa_recipe.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.ecutb.magnus.jpa_recipe.model.entity.Ingredient;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class IngredientRepositoryTest {

    @Autowired
    private IngredientRepository ingredientRepository;

    private Ingredient testIngredient1;
    private Ingredient testIngredient2;
    private Ingredient testIngredient3;

    @BeforeEach
    void setUp() {
        testIngredient1 = ingredientRepository.save(testIngredient1 = new Ingredient("TestIngredientOne"));
        testIngredient2 = ingredientRepository.save(testIngredient2 = new Ingredient("TestIngredientTwo"));
    }

    @Test
    public void findByIngredientName_test(){
        String expected = "TestIngredientOne";
        Optional<Ingredient> result = ingredientRepository.findByIngredientName("TestIngredientOne");

        assertNotNull(result);
        assertEquals(expected,result.get().getIngredientName());
    }

    @Test
    public void findByIngredientNameContains_test(){
        List<Ingredient> result = ingredientRepository.findByIngredientNameContains("TestIngredient");

        assertNotNull(result);
        assertEquals(2,result.size());
    }
}