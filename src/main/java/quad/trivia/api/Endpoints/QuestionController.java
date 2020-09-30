package quad.trivia.api.Endpoints;

import org.springframework.web.bind.annotation.*;
import quad.trivia.api.Models.Result;
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
    public Result questions(@RequestParam(required = false) Map<String, String> parameters){
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
    public Result checkAnswers(@RequestBody Result answers){
        return Repository.getResultRepository().GetCorrectAnswers(Integer.valueOf(answers.getId()), answers.getResults());
    }

    /**
     * Checks the given answers with the given ID against our locally saved questions with the answers
     * @param answer is a individual question answered
     * @return The correct questions
     */
    @PostMapping(value = "checkanswer", consumes = "application/json")
    @ResponseBody
    public boolean checkanswer(@RequestBody Result answer){
        return Repository.getResultRepository().GetCorrectAnswer(Integer.valueOf(answer.getId()), answer.getResults().get(0));
    }
}
