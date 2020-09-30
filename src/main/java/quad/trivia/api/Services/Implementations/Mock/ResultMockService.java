package quad.trivia.api.Services.Implementations.Mock;

import quad.trivia.api.Models.Question;
import quad.trivia.api.Models.Result;
import quad.trivia.api.Services.Interfaces.ResultServiceInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultMockService implements ResultServiceInterface {

    Map<Integer, Result> results = new HashMap<>();
    int id = 1;

    @Override
    public Result GetQuestions(Map<String, String> parameters) {

        Result result = new Result();
        String[] incorrect1 = new String[3];
        incorrect1[0] = "Clyde";
        incorrect1[1] = "Tweed";
        incorrect1[2] = "Dee";
        Question question1 = new Question();
        question1.setCategory("General Knowledge");
        question1.setType("multiple");
        question1.setDifficulty("medium");
        question1.setQuestion("Which river flows through the Scottish city of Glasgow?");
        question1.setCorrect_answer("Tay");
        question1.setIncorrect_answers(incorrect1);

        String[] incorrect2 = new String[3];
        incorrect2[0] = "1992";
        incorrect2[1] = "1994";
        incorrect2[2] = "1990";
        Question question2 = new Question();
        question2.setCategory("Entertainment: Japanese Anime & Manga");
        question2.setType("multiple");
        question2.setDifficulty("medium");
        question2.setQuestion("What year did &quot;Bishoujo Senshi Sailor Moon&quot; air in Japan?");
        question2.setCorrect_answer("1989");
        question2.setIncorrect_answers(incorrect2);

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        result.setResults(questions);
        result.setResponse_code("0");

        result.setId(String.valueOf(id));
        addResult(result);
        return result;
    }

    @Override
    public Result GetResults(int id) {
        return results.get(id);
    }

    @Override
    public void addResult(Result result) {
        result.setId(String.valueOf(id));
        results.put(id,result);
        id++;
    }

}
