package se.ecutb.magnus.jpa_recipe.model.entity;

import org.hibernate.annotations.GenericGenerator;
import se.ecutb.magnus.jpa_recipe.model.Measurement;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class RecipeIngredient {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String recipeIngredientId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
    private double measurementAmount;
    private Measurement measurement;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public RecipeIngredient(Ingredient ingredient, double measurementAmount, Measurement measurement) {
        this.ingredient = ingredient;
        this.measurementAmount = measurementAmount;
        this.measurement = measurement;
    }


    public RecipeIngredient(Ingredient ingredient){
        this(ingredient,0,null);
    }


    public RecipeIngredient(){}

    public String getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getMeasurementAmount() {
        return measurementAmount;
    }

    public void setMeasurementAmount(double measurementAmount) {
        this.measurementAmount = measurementAmount;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return Double.compare(that.measurementAmount, measurementAmount) == 0 &&
                Objects.equals(recipeIngredientId, that.recipeIngredientId) &&
                Objects.equals(ingredient, that.ingredient) &&
                measurement == that.measurement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeIngredientId, ingredient, measurementAmount, measurement);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RecipeIngredient{");
        sb.append("recipeIngredientId='").append(recipeIngredientId).append('\'');
        sb.append(", ingredient=").append(ingredient);
        sb.append(", measurementAmount=").append(measurementAmount);
        sb.append(", measurement=").append(measurement);
        sb.append('}');
        return sb.toString();
    }
}