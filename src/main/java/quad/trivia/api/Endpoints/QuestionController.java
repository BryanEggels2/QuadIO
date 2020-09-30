package quad.trivia.api.Endpoints;

import org.springframework.web.bind.annotation.*;
import quad.trivia.api.Models.ResultIn;
import quad.trivia.api.Models.ResultOut;
import quad.trivia.api.Repositories.Repository;

import java.util.Map;

@RestController
public class QuestionController {

    /**
     * Gets questions with desired parameters. If no parameters are given, push a default of 5 questions
     * @param parameters
     * @return Result with an ID
     */
    @GetMapping("/questions")
    public ResultIn questions(@RequestParam(required = false) Map<String, String> parameters){
        return (parameters.size() == 0) ?
                Repository.getResultRepository().GetQuestions() :
                Repository.getResultRepository().GetQuestions(parameters);
    }

    /**
     * Checks the given answers with the given ID against our locally saved questions with the answers
     * @param answers
     * @return The correct questions
     */
    @PostMapping(value = "checkanswers", consumes = "application/json")
    @ResponseBody
    public ResultOut checkAnswers(@RequestBody ResultIn answers){
        return Repository.getResultRepository().GetCorrectAnswers(Integer.parseInt(answers.getId()), answers.getResults());
    }

    /**
     * Checks the given answers with the given ID against our locally saved questions with the answers
     * @param answer is a individual question answered
     * @return The correct questions
     */
    @PostMapping(value = "checkanswer", consumes = "application/json")
    @ResponseBody
    public boolean checkanswer(@RequestBody ResultIn answer){
        return Repository.getResultRepository().GetCorrectAnswer(Integer.valueOf(answer.getId()), answer.getResults().get(0));
    }
}
