package mapper;

import api.Api;
import module.Ingredient;
import module.Meal;
import module.SpecificMeal;

import java.util.*;

public class JsonMapper {

    public List<Ingredient> parseIngredients(){
        String res = Api.requestIngredients();
        List<Ingredient> ingredientsList = new ArrayList<>();

        String[] newResponse = res.split("},");

        for (String string : newResponse) {
            String[] arr = string.split("\",");
            String[] ingredients = arr[1].split(":\"");
            String[] ids = arr[0].split(":\"");
            ingredientsList.add(new Ingredient(ids[1], ingredients[1].replace(" ", "-")));
        }

        return ingredientsList;
    }

    public List<Meal> parseMeals(String s) {
        String res = Api.requestMealsList(s);

        List<Meal> meals = new ArrayList<>();

        String[] newRes = res.split("},");
        if (newRes.length <= 1){
            throw new RuntimeException("Sorry, no meals with this main ingredient");
        }
        for (String string : newRes) {
            String[] arr = string.split(",\"");
            String[] id = arr[2].split("\":\"");
            String[] name = arr[0].split("\":\"");
            meals.add(new Meal(id[1].replace("\"}]}", "").replace("\"", ""), name[1].replace("\"", "")));
        }

        return meals;
    }
    public SpecificMeal parseSpecificMeal(int id){
        String res = Api.requestSpecificMealById(id);
        if (res.length() <= 1){
            throw new RuntimeException("Sorry, couldn't find it.");
        }
        List<String> ingredientNames = new ArrayList<>();
        Map<String, String> ingredients = new HashMap<>();
        SpecificMeal meal = new SpecificMeal();
        String[] keyValuePairs = res.split("\",");
        int i = 0;
        for (String keyValuePair : keyValuePairs) {
            String[] splitPair = keyValuePair.split("\":");
            String key = splitPair[0];
            String value = splitPair[1];
            if (value.equals("\"")){
                continue;
            }
            if (key.equals("\"strMeal")){
                meal.setMealName(value.replace("\"", ""));
            } else if (key.contains("Instructions")) {
                meal.setInstructions(value.replace("\"", "")
                        .replace("\\r", "")
                        .replace("\\n", "")
                        .replace("\\u2019", "")); //API returned instructions with those special symbols.
            } else if (key.contains("Ingredient")) {
                ingredientNames.add(value.replace("\"", "")); // Storing in a list, so I can then grab the name value, and add it with the measure in the map.
            } else if (key.contains("Measure")) {
                if (value.equals("\" ")){
                    continue;
                }
                ingredients.put(ingredientNames.get(i), value.replace("\"", ""));
                i++;
            }
        }
        meal.setIngredients(ingredients);
        return meal;
    }
}
