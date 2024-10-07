package ru.skypro.questions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuestionIsNotExistException extends RuntimeException {

    private final String question;
    private final String answer;


    public QuestionIsNotExistException(String question, String answer) {
        super("Question: %s, with answer: %s is not exist".formatted(question, answer));
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
