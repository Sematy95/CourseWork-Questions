package ru.skypro.questions.service.imp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.skypro.questions.domain.Question;
import ru.skypro.questions.exceptions.QuestionAlreadyExistException;
import ru.skypro.questions.exceptions.QuestionIsNotExistException;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private final JavaQuestionService javaQuestionService = new JavaQuestionService();

    Question question = new Question("Cycles", "Yes");
    Question question2 = new Question("Methods", "Yes");
    Question question3 = new Question("Spring", "Yes");



    @DisplayName("Положительный тест на добавление вопроса")
    @Test
    void shouldAddQuestion_WhenCorrectQuestion_ThenAdd() {

        //test
        Question actual = javaQuestionService.add(question);

        //check
        Collection<Question> result = javaQuestionService.getAll();
        assertEquals(actual, question);//проверяем, что вернулся именно тот вопрос, который мы добавили
        assertEquals(result.size(), 1);// проверяем, что добавили именно 1 вопрос
        assertEquals((result.iterator().next()), question);//проверяем, что в коллекции именно тот вопрос
    }

    @DisplayName("Негативный тест на добавление вопроса - добавляем вопрос, который уже есть в коллекции")
    @Test
    void shouldAddQuestion_WhenQuestionIsAlreadyAdded_ThenThrowException() {
        //test&check
        Question actual = javaQuestionService.add(question);
        assertThrows(QuestionAlreadyExistException.class, () -> javaQuestionService.add(question));//проверяем, что кидает правильную ошибку
    }

    @DisplayName("Положительный тест на добавление вопроса - добавляем второй вопрос, проверяем, что ошибки нет")
    @Test
    void shouldAddQuestion_WhenAnotherQuestion_ThenAdd() {
        //test&check
        Question actual = javaQuestionService.add(question);
        assertDoesNotThrow(() -> javaQuestionService.add(question2));//проверяем, что не кидает ошибку
    }

    @DisplayName("Положительный тест на добавление вопроса с вопросом и ответом")
    @Test
    void shouldAddQuestionWithAnswer_WhenCorrectQuestion_ThenAdd() {

        //test
        Question actual = javaQuestionService.add(question.getQuestion(), question.getAnswer());

        //check
        Collection<Question> result = javaQuestionService.getAll();
        assertEquals(actual, question);//проверяем, что вернулся именно тот вопрос, который мы добавили
        assertEquals(result.size(), 1);// проверяем, что добавили именно 1 вопрос
        assertEquals((result.iterator().next()), question);//проверяем, что в коллекции именно тот вопрос
    }

    @DisplayName("Негативный тест на добавление вопроса с вопросом и ответом  - добавляем вопрос, который уже есть в коллекции")
    @Test
    void shouldAddQuestionWithAnswer_WhenQuestionIsAlreadyAdded_ThenThrowException() {
        //test&check
        Question actual = javaQuestionService.add(question.getQuestion(), question.getAnswer());
        assertThrows(QuestionAlreadyExistException.class, () -> javaQuestionService.add(question));//проверяем, что кидает правильную ошибку
    }

    @DisplayName("Положительный тест на добавление вопроса с вопросом и ответом - добавляем второй вопрос, проверяем, что ошибки нет")
    @Test
    void shouldAddQuestionWithAnswer_WhenAnotherQuestion_ThenAdd() {
        //test&check
        Question actual = javaQuestionService.add(question.getQuestion(), question.getAnswer());
        assertDoesNotThrow(() -> javaQuestionService.add(question2));//проверяем, что не кидает ошибку
    }

    @DisplayName("Положительный тест на удаление вопроса ")
    @Test
    void shouldRemoveQuestion_WhenCorrectQuestion_ThenRemove() {
        //test
        javaQuestionService.add(question);
        Question actual = javaQuestionService.remove(question.getQuestion(), question.getAnswer());
        Collection<Question> result = javaQuestionService.getAll();

        //Check
        assertTrue(javaQuestionService.getAll().isEmpty());//проверяем, что лист пустой и все удалилось
        assertEquals(actual, question);//проверяем, что метод вернул тот вопрос, который мы хотели удалить
    }

    @DisplayName("Отрицательный тест на удаление - пытаемся удалить тест, которого нет в коллекции  ")
    @Test
    void shouldRemoveQuestion_WhenQuestionIsNotExist_ThenThrowException() {
        //test&check
        assertThrows(QuestionIsNotExistException.class, () -> javaQuestionService.remove(question3.getQuestion(), question3.getAnswer()));//проверяем, что кидает правильную ошибку
    }

    @DisplayName("Положительный тест на вызов списка вопросов")
    @Test
    void ShouldGetAll() {
        javaQuestionService.add(question);
        javaQuestionService.add(question2);
        javaQuestionService.add(question3);

        //test
        Collection<Question> actual = javaQuestionService.getAll();

        //check
        assertEquals(3, actual.size());
        assertTrue(actual.contains(question) & actual.contains(question2) & actual.contains(question3));

    }

    @DisplayName("Тест на вызов случайного вопроса")
    @Test
    void shouldReturnRandomQuestion() {
        javaQuestionService.add(question);
        javaQuestionService.add(question2);
        javaQuestionService.add(question3);

        Collection<Question> expected = javaQuestionService.getAll();

        //test
        Question actual = javaQuestionService.getRandomQuestion();

        assertTrue(expected.contains(actual));

    }
}