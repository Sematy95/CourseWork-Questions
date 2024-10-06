package ru.skypro.questions.service.imp;

import org.springframework.stereotype.Service;
import ru.skypro.questions.domain.Question;
import ru.skypro.questions.exceptions.QuestionAmountIsTooMuchException;
import ru.skypro.questions.service.ExaminerService;
import ru.skypro.questions.service.QuestionService;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;
    private final Random random = new Random();

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {


        if (amount < 0 || amount > questionService.getAll().size()) {
            throw new QuestionAmountIsTooMuchException(amount);
        }
        if (amount == questionService.getAll().size()) {
            return questionService.getAll();
        }
        Collection<Question> questionList = new ArrayList<>(questionService.getAll());

    while (questionList.size() > amount ) {
         int i = random.nextInt(questionList.size()-1);
        questionList.remove(((ArrayList<Question>) questionList).get(i));
    }
        return questionList;

    }

}
