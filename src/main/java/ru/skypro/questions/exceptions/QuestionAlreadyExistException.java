package ru.skypro.questions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionAlreadyExistException extends RuntimeException {

    private final String question;
    private final String answer;

    public QuestionAlreadyExistException(String question, String answer) {
        super("Question: %s, with answer: %s is already exist in list of questions".formatted(question, answer));
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
