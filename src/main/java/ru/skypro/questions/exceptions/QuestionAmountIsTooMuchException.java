package ru.skypro.questions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionAmountIsTooMuchException extends RuntimeException {

    private final int amount;

    public QuestionAmountIsTooMuchException(int amount) {
        super("Required amount of questions: %s is more than are stored in service or less than 0 ".formatted(amount));
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
