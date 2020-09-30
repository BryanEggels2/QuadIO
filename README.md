# Quad interview


### Docker
```sh
$ docker build -t quad/trivia .
$ docker run -p 8080:8080 quad/opentdb
```

now you can navigate to localhost:8080/questions

## Endpoints
The endpoints will be defined here

### /questions
Accepts a map of parameters. For example:
localhost:8080/questions?amount=10&category=9&difficulty=easy&type=boolean

You can find all the possible parameters here: https://opentdb.com/api_config.php

If you do not give any parameters, the /questions endpoint will give back five random questions.

### /giveanswer
Accepts a result object with an ID and multiple questions. the givenAnswers field has to be provided for the questions.
Example:
```
{
    "id": "2",
    "results": [
        {
            "category": "General Knowledge",
            "type": "multiple",
            "difficulty": "medium",
            "question": "Which river flows through the Scottish city of Glasgow?",
			"givenAnswer": "Dee",
            "possibleAnswers": [
                "Clyde",
                "Dee",
                "Tweed",
                "Tay"
            ]
        },
        {
            "category": "Entertainment: Japanese Anime & Manga",
            "type": "multiple",
            "difficulty": "medium",
            "question": "What year did &quot;Bishoujo Senshi Sailor Moon&quot; air in Japan?",
			"givenAnswer": "1989",
            "possibleAnswers": [
                "1990",
                "1992",
                "1989",
                "1994"
            ]
        }
    ]
}
```
This endpoint returns a list of correct and incorrect answered questions.


## /checkanswer
Accepts a result like the previous, but only with one question in it to see if that specific answer is correct. Returns true or false.
