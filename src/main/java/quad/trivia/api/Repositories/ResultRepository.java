package quad.trivia.api.Repositories;

import quad.trivia.api.Models.Question;
import quad.trivia.api.Models.ResultIn;
import quad.trivia.api.Models.ResultOut;
import quad.trivia.api.Services.Interfaces.ResultServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultRepository implements ResultServiceInterface {

    ResultServiceInterface serviceInterface;

    /**
     * Make this protected to only allow it to be created by something in this package.
     * @param serviceInterface is the implemented service it is going to use.
     */
    protected ResultRepository(ResultServiceInterface serviceInterface){
        this.serviceInterface = serviceInterface;
    }

    /**
     * Gets questions from the API with the specified parameters
     * @param parameters
     * @return
     */
    @Override
    public ResultIn GetQuestions(Map<String, String> parameters) {
        return serviceInterface.GetQuestions(parameters);
    }

    /**
     * Gets the desired result from the used service.
     * @param id identifier of the result
     * @return result
     */
    @Override
    public ResultIn GetResults(int id) {
        return serviceInterface.GetResults(id);
    }

    /**
     * Saves the result somewhere in the service.
     * @param result is the result you want to save.
     */
    @Override
    public void addResult(ResultIn result) {
        serviceInterface.addResult(result);
    }

    /**
     * This is the default 'getQuestions' method which gets 5 questions
     * @return a default of 5 questions
     */
    public ResultIn GetQuestions() {
        Map defaultvalues = new HashMap<String, String>();
        defaultvalues.put("amount", "5");
        return serviceInterface.GetQuestions(defaultvalues);
    }

    /**
     *
     * @param id The ID of the result you'd want to check
     * @param answered_questions The questions send in by the user
     * @return the correct questions in the ResultOut class
     */
    public ResultOut GetCorrectAnswers(int id, List<Question> answered_questions) {
        ResultIn savedResult = Repository.getResultRepository().GetResults(id);
        ResultOut resultOut = new ResultOut();

        //resets the amount of correct answers so you can check your set of questions multiple times
        savedResult.ResetCorrect();
        for (int i = 0; i < answered_questions.size(); i++){
            String givenAnswer = answered_questions.get(i).getgivenAnswer();
            String correctAnswer = savedResult.getResults().get(i).getCorrect_answer();
            savedResult.getResults().get(i).setgivenAnswer(givenAnswer);
            if (givenAnswer.equals(correctAnswer)) {
                resultOut.getCorrectAnswers().add(savedResult.getResults().get(i));
                savedResult.CorrectPlusOne();
                resultOut.CorrectPlusOne();
            }
            else{
                resultOut.getWrongAnswers().add(savedResult.getResults().get(i));
            }

        }
        return resultOut;
    }

    /**
     * Get the feedback if one specific asked question is correct
     * @param id is the id of the result
     * @param q is the question the user wants to check
     * @return true | false
     */
    public boolean GetCorrectAnswer(int id, Question q){
        ResultIn savedResult = Repository.getResultRepository().GetResults(id);

        for (int i=0; i < savedResult.getResults().size(); i++ ){
            if (savedResult.getResults().get(i).getQuestion().equals(q.getQuestion())){
                if (savedResult.getResults().get(i).getCorrect_answer().equals(q.getgivenAnswer())){
                    savedResult.getResults().get(i).setgivenAnswer(q.getgivenAnswer());
                    return true;
                }
                break;
            }
        }
        return false;
    }
}
