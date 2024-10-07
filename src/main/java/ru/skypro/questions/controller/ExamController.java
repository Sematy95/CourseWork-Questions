package ru.skypro.questions.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.questions.domain.Question;
import ru.skypro.questions.service.ExaminerService;
import java.util.Collection;

@RestController
@RequestMapping
public class ExamController {

    private final ExaminerService examinerService;


    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/{amount}")
    Collection<Question> getQuestions(@PathVariable("amount") int amount) {
        return examinerService.getQuestions(amount);
    }
}
