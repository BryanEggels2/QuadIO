package quad.trivia.api.Endpoints;

import org.junit.jupiter.api.Test;
import quad.trivia.api.Models.Question;
import quad.trivia.api.Models.ResultIn;
import quad.trivia.api.Models.ResultOut;
import quad.trivia.api.Repositories.Repository;
import quad.trivia.api.Services.Service;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuestionControllerTest {

    @Test
    public void Amountquestions(){
        Repository.initialize(Service.Api);
        QuestionController controller = new QuestionController();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("amount", "500");
        ResultIn result = controller.questions(parameters);
        result.getResults();
        assertEquals(50, result.getResults().size());
    }

    @Test
    public void Difficulty(){
        Repository.initialize(Service.Api);
        QuestionController controller = new QuestionController();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("amount", "50");
        parameters.put("difficulty", "easy");
        //category 9 is general knowledge
        parameters.put("category", "9");
        parameters.put("type", "multiple");
        ResultIn result = controller.questions(parameters);
        result.getResults();
        for (Question question: result.getResults()) {
            if(!question.getDifficulty().equals("easy")){
                fail("not all questions's difficulty is set to easy");
            }
        }
        //this means the above for loop has worked and the difficulty of all questions is set to easy
        assertTrue(true);
    }

    @Test
    public void Category(){
        Repository.initialize(Service.Api);
        QuestionController controller = new QuestionController();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("amount", "50");
        parameters.put("difficulty", "easy");
        //category 9 is general knowledge
        parameters.put("category", "9");
        parameters.put("type", "multiple");
        ResultIn result = controller.questions(parameters);
        result.getResults();

        for (Question question: result.getResults()) {
            if(!question.getCategory().equals("General Knowledge")){
                fail("not all categories are set to General Knowledge");
            }
        }
        //this means the above for loop has worked and the difficulty of all questions is set to easy
        assertTrue(true);
    }

    @Test
    public void Type(){
        Repository.initialize(Service.Api);
        QuestionController controller = new QuestionController();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("amount", "50");
        parameters.put("difficulty", "easy");
        //category 9 is general knowledge
        parameters.put("category", "9");
        parameters.put("type", "multiple");
        ResultIn result = controller.questions(parameters);
        result.getResults();

        for (Question question: result.getResults()) {
            if(!question.getType().equals("multiple")){
                fail("not all questions are of the type 'multiple'");
            }
        }
        //this means the above for loop has worked and the difficulty of all questions is set to easy
        assertTrue(true);
    }


    @Test
    public void GeneralTest() {
        Repository.initialize(Service.Api);
        QuestionController controller = new QuestionController();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("amount", "32");
        parameters.put("difficulty", "hard");
        //category 9 is general knowledge
        parameters.put("category", "8");
        parameters.put("type", "boolean");
        ResultIn result = controller.questions(parameters);
        result.getResults();

        for (Question question : result.getResults()) {
            if (!question.getType().equals("boolean")) {
                fail("not all questions are of the type 'multiple'");
            }
        }
        for (Question question : result.getResults()) {
            if (!question.getCategory().equals("Entertainment: Film")) {
                fail("not all questions are of the category 'Entertainment: Film'");
            }
        }
        for (Question question : result.getResults()) {
            if (!question.getDifficulty().equals("hard")) {
                fail("not all questions are of the difficulty 'hard'");
            }
            //this means the above for loop has worked and the difficulty of all questions is set to easy
            assertEquals(32, result.getResults().size());
        }

    }
    @Test
    //create a full cycle test here to prove my API is working
    public void FullTestAllCorrect() {
        Repository.initialize(Service.Api);
        QuestionController controller = new QuestionController();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("amount", "5");
        parameters.put("difficulty", "hard");
        //category 9 is general knowledge
        parameters.put("category", "9");
        parameters.put("type", "boolean");
        ResultIn result = controller.questions(parameters);

        for (Question q : result.getResults()){
            q.setgivenAnswer(q.getCorrect_answer());
        }
        ResultOut r = controller.checkAnswers(result);
        assertEquals(5, r.getAmountCorrect());



    }
    @Test
    public void FullTestOneCorrect() {
        Repository.initialize(Service.Api);
        QuestionController controller = new QuestionController();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("amount", "2");
        parameters.put("difficulty", "hard");
        //category 9 is general knowledge
        parameters.put("category", "9");
        parameters.put("type", "boolean");
        ResultIn result = controller.questions(parameters);


        result.getResults().get(0).setgivenAnswer(result.getResults().get(0).getCorrect_answer());
        result.getResults().get(1).setgivenAnswer("wrong");
        ResultOut r = controller.checkAnswers(result);
        assertEquals(1, r.getAmountCorrect());



    }



}