package mapper;

import api.Api;
import module.Ingredient;
import module.Meal;

import java.util.ArrayList;
import java.util.List;

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
            String[] arr = string.split("\",\"");
            String[] id = arr[2].split("\":\"");
            String[] name = arr[0].split("\":\"");
            meals.add(new Meal(name[1], id[1].replace("\"}]}", "").replace("\"", "")));
        }

        return meals;
    }
}
