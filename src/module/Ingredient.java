package module;

public class Ingredient {

    private String id;

    private String strIngredient;

    private String decription;

    public Ingredient(String id, String strIngredient) {
        this.id = id;
        this.strIngredient = strIngredient;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Ingredient(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }
}
