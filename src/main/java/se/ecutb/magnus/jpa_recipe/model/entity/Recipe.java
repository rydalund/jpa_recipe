package se.ecutb.magnus.jpa_recipe.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;
    private String recipeName;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST}, orphanRemoval = true, mappedBy = "recipe")
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinColumn(name = "instruction_id")
    private RecipeInstruction recipeInstruction;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinTable(name = "recipe_recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "recipe_category_id"))
    private List<RecipeCategory> categories = new ArrayList<>();

    public Recipe(int recipeId, String recipeName, List<RecipeIngredient> recipeIngredients, RecipeInstruction recipeInstruction, List<RecipeCategory> categories) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        setRecipeIngredients(recipeIngredients);
        this.recipeInstruction = recipeInstruction;
        setCategories(categories);
    }

    public Recipe(String recipeName, List<RecipeIngredient> recipeIngredients, RecipeInstruction recipeInstruction, List<RecipeCategory> categories) {
        this(0, recipeName, recipeIngredients, recipeInstruction, categories);
    }

    public Recipe(String recipeName, RecipeInstruction recipeInstruction) {
        this(0, recipeName, null, recipeInstruction, null);
    }

    public Recipe(String recipeName) {
        this(0, recipeName, null, null, null);
    }

    public Recipe(){}

    public int getRecipeId() {
        return recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        if (recipeIngredients == null) {
            recipeIngredients = new ArrayList<>();
        }
        this.recipeIngredients = recipeIngredients;
    }

    public RecipeInstruction getRecipeInstruction() {
        return recipeInstruction;
    }

    public void setRecipeInstruction(RecipeInstruction recipeInstruction) {
        this.recipeInstruction = recipeInstruction;
    }

    public List<RecipeCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<RecipeCategory> categories) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        this.categories = categories;
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
        try {
            recipeIngredients.add(recipeIngredient);
            recipeIngredient.setRecipe(this);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeRecipeIngredient(RecipeIngredient recipeIngredient){
        try {
            //recipeIngredient.setIngredient(null);
            recipeIngredients.remove(recipeIngredient);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void addRecipeCategory(RecipeCategory recipeCategory){
        try {
            if (!categories.contains(recipeCategory)) {
                categories.add(recipeCategory);
            } else {
                System.out.println("Category already exist");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void removeRecipeCategory(RecipeCategory recipeCategory){
        try {
            if (!categories.contains(recipeCategory)) {
                System.out.println("Category was not present");
            } else {
                categories.remove(recipeCategory);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return recipeId == recipe.recipeId &&
                Objects.equals(recipeName, recipe.recipeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, recipeName, recipeInstruction);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Recipe{");
        sb.append("recipeId=").append(recipeId);
        sb.append(", recipeName='").append(recipeName).append('\'');
        sb.append(", recipeIngredientList=").append(recipeIngredients);
        sb.append(", recipeInstruction=").append(recipeInstruction);
        sb.append(", recipeCategoryList=").append(categories);
        sb.append('}');
        return sb.toString();
    }
}