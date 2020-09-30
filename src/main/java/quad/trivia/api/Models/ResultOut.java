package quad.trivia.api.Models;

import java.util.ArrayList;
import java.util.List;

public class ResultOut {

    private int amountCorrect = 0;
    private List<Question> correctAnswers;
    private List<Question> wrongAnswers;

    public ResultOut() {
        correctAnswers = new ArrayList<>();
        wrongAnswers = new ArrayList<>();
    }

    public int getAmountCorrect() {
        return amountCorrect;
    }

    public void setAmountCorrect(int amountCorrect) {
        this.amountCorrect = amountCorrect;
    }

    public List<Question> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<Question> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public List<Question> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(List<Question> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public void CorrectPlusOne(){
        amountCorrect++;
    }
}
