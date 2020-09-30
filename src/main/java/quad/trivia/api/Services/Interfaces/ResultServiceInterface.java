package quad.trivia.api.Services.Interfaces;

import quad.trivia.api.Models.Result;


import java.util.Map;
/**
 * this interface makes sure you can use any input source you want
 * (in case you want to switch from an API to a DB or something crazy. Also usefull for mocking.)
*/
public interface ResultServiceInterface {

    Result GetQuestions(Map <String, String> parameters);

    Result GetResults(int id);

    void addResult(Result result);
}
