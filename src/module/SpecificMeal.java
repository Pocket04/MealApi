package module;

import java.util.List;
import java.util.Map;

public class SpecificMeal {

    private String mealName;

    private String instructions;

    private Map<String, String> ingredients;

    public SpecificMeal(String id, String name, String mealName, String instructions, Map<String, String> ingredients) {
        this.mealName = mealName;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public SpecificMeal() {
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, String> ingredients) {
        this.ingredients = ingredients;
    }
}
