package api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api {

    public static String requestIngredients(){

        try{
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.themealdb.com/api/json/v1/1/list.php?i=list"))
                    .GET().build();

            HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());

            return res.body();

        }catch(Exception e){
            System.out.println("Exception: " + e);
        }
        return "Sorry, no recipes";
    }
    public static String requestMealsList(String endpoint){

        try{
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.themealdb.com/api/json/v1/1/filter.php?i=" + endpoint)).GET().build();
            System.out.println("https://www.themealdb.com/api/json/v1/1/filter.php?i=" + endpoint);

            HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());

            return res.body();

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return "naw m8";
    }

}
