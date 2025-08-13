import java.util.ArrayList;
import java.util.List;

public class JsonMapper {

    private final String res;

    public JsonMapper(){
        res = Api.request();
    }

    public List<Ingredient> parseIngredients(){
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
}
