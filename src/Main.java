import java.util.List;

public class Main {
    public static void main(String[] args) {
        String res = Api.request();
        JsonMapper jsonMapper = new JsonMapper();
        List<Ingredient> ingrs = jsonMapper.parseIngredients();
        for (Ingredient ingr : ingrs) {
            System.out.println(ingr.getStrIngredient());
        }
    }
}
