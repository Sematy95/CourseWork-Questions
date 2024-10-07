package ru.skypro.questions.service.imp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.questions.domain.Question;
import ru.skypro.questions.exceptions.QuestionAmountIsTooMuchException;

import java.util.Collection;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    Question question = new Question("Cycles", "Yes");
    Question question2 = new Question("Methods", "Yes");
    Question question3 = new Question("Spring", "Yes");

    Collection<Question> questions = List.of(question, question2, question3);

    @DisplayName("Положительный тест - возвращает верное число рандомных вопросов ")
    @Test
    void getQuestions() {
        when(javaQuestionService.getAll()).thenReturn(questions);
        int amount = 2;

        //test
        Collection<Question> actual = examinerService.getQuestions(amount);

        //check
        assertEquals(amount, actual.size()); // проверяем, что количество вопросов равно запрашиваемому
        assertTrue(actual.contains(question) || actual.contains(question2) || actual.contains(question3));//проверяем, что полученные вопросы содержат вопросы из исходного списка
    }

    @DisplayName("Негативный тест - выкидывает ошибку, если требуемое число вопросов больше, чем в списке")
    @Test
    void getQuestionsWithIncorrectAmount() {
        when(javaQuestionService.getAll()).thenReturn(questions);
        int amount = 4;

        //test&check
        assertThrows(QuestionAmountIsTooMuchException.class, () -> examinerService.getQuestions(amount));//Проверяем, что выкидывает правильную ошибку
    }

    @DisplayName("Позитивный тест - возвращает исходный список вопросов, если требуемое количество вопросов равно количеству воуросов в исходном списку")
    @Test
    void getQuestionsWithEqualAmount() {
        when(javaQuestionService.getAll()).thenReturn(questions);
        int amount = 3;

        //test&check
        assertIterableEquals(questions, examinerService.getQuestions(amount));//сравниваем исходную коллекцию и полученную
    }
}