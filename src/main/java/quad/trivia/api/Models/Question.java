package quad.trivia.api.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Question {

    //these are the fields needed to generate the model with the received json
    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private String[] incorrect_answers;

    //these are the fields needed to check the answer
    private String givenAnswer;
    private ArrayList<String> possibleAnswers;


    public String getgivenAnswer() {
        return givenAnswer;
    }

    public void setgivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    public ArrayList<String> getPossibleAnswers() {
        if(possibleAnswers == null){
            ArrayList<String> possibleAnswers = new ArrayList<>();
            possibleAnswers.addAll(Arrays.asList(incorrect_answers));
            possibleAnswers.add(correct_answer);
            Collections.shuffle(possibleAnswers);
            this.possibleAnswers = possibleAnswers;
            return this.possibleAnswers;
        }
        return possibleAnswers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @JsonIgnore
    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public void setIncorrect_answers(String[] incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }
}
