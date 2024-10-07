package ru.skypro.questions.service;

import ru.skypro.questions.domain.Question;
import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);


}
