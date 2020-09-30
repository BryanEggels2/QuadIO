package quad.trivia.api.Services.Implementations.Api;

import quad.trivia.api.Models.Result;
import quad.trivia.api.Services.Interfaces.ResultServiceInterface;
import com.google.gson.Gson;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is the API implementation of the ResultServiceInterface
 */
public class ResultAPIService implements ResultServiceInterface {

    Map<Integer, Result> results = new HashMap<>();
    int id = 1;


    /**
     * This method gets the questions from the opentdb API.
     * You can pass the desired parameters in the map
    */
    @Override
    public Result GetQuestions(Map<String, String> parameters) {
        if(Integer.valueOf(parameters.get("amount")) > 50 ){
            parameters.put("amount", "50");
        }
        Result result = new Result();
        try{
            //this piece of code builds the URL with the desired parameters
            URIBuilder builder = new URIBuilder("https://opentdb.com/api.php");
            for (Map.Entry<String, String> entry : parameters.entrySet())
            {
                builder.setParameter(entry.getKey(), entry.getValue());
            }
            String json = Request.Get(builder.build())
                    .connectTimeout(1000)
                    .socketTimeout(1000)
                    .execute().returnContent().asString();
            //convert the request Json to a class using GSON (Google's own json converter)
            result = new Gson().fromJson(json, Result.class);
        } catch (ClientProtocolException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } catch (URISyntaxException e) {
            e.getMessage();
        }
        result.setId(String.valueOf(id));
        addResult(result);
        return result;
    }

    /**
     *
     * @param id The id of the desired result you'd want to get
     * @return result
     */
    @Override
    public Result GetResults(int id) {
        try{
            return results.get(id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Normally you'd add the result to a desired Database here.
     * But for PoC sake, it's added to a list.
     * @param result
     */
    @Override
    public void addResult(Result result) {
        results.put(id,result);
        id++;
    }
}
