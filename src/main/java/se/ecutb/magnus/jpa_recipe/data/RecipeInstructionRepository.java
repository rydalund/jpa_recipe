package se.ecutb.magnus.jpa_recipe.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.ecutb.magnus.jpa_recipe.model.entity.RecipeInstruction;

@Repository
public interface RecipeInstructionRepository extends CrudRepository<RecipeInstruction, String> {
}
