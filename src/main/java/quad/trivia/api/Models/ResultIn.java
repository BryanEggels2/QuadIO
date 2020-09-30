package quad.trivia.api.Models;

import java.util.ArrayList;
import java.util.List;

public class ResultIn {
    private String id;
    private int correct = 0;


    public int getCorrect() {
        return correct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String response_code;
    private List<Question> results;

    public List<Question> getResults() {
        return results;
    }

    public void setResults(List<Question> results) {
        this.results = results;
    }

    public String getResponse_code() {
        return response_code;
    }

    public void setResponse_code(String response_code) {
        this.response_code = response_code;
    }

    public void CorrectPlusOne(){
        correct++;
    }
    public void ResetCorrect(){correct = 0;}

    public void AddCorrectAnswer(Question q) {

    }
}
