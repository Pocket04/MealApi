import api.Api;
import mapper.JsonMapper;
import module.Ingredient;
import module.Meal;
import module.SpecificMeal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JsonMapper jsonMapper = new JsonMapper();
        List<Ingredient> ingrs = jsonMapper.parseIngredients();

        for (Ingredient ingr : ingrs) {
            System.out.println(ingr.getStrIngredient());
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("Which ingredient would you like to look up?");
        String ingredient = scan.nextLine();

        List<Meal> meals = new ArrayList<>();

        try{

            meals = jsonMapper.parseMeals(ingredient);

        }catch (RuntimeException e){

            System.out.println(e.getMessage());
        }

        int i = 1;

        for (Meal meal : meals) {
            System.out.println(i++ + ".");
            System.out.println("ID: " + meal.getId());
            System.out.println(meal.getName());
        }
        System.out.println("Please insert the ID of your selected meal: ");
        int id = Integer.parseInt(scan.nextLine());
        SpecificMeal specMeal = jsonMapper.parseSpecificMeal(id);
        System.out.println(specMeal.getMealName());
        specMeal.getIngredients().forEach( (ingr, measure) -> System.out.printf("%s - %s %s", ingr, measure, System.lineSeparator()));
        System.out.println(specMeal.getInstructions());
    }
}
