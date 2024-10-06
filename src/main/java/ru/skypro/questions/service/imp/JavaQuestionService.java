package ru.skypro.questions.service.imp;

import org.springframework.stereotype.Service;
import ru.skypro.questions.domain.Question;
import ru.skypro.questions.exceptions.QuestionAlreadyExistException;
import ru.skypro.questions.exceptions.QuestionIsNotExistException;
import ru.skypro.questions.service.QuestionService;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        Question questionGenerated = new Question(question, answer);

        if (questions.contains(questionGenerated)) {
            throw new QuestionAlreadyExistException(questionGenerated.getQuestion(),questionGenerated.getAnswer());
        }
        questions.add(questionGenerated);
        return questionGenerated;
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new QuestionAlreadyExistException(question.getQuestion(),question.getAnswer());
        }
        questions.add(question);
        return question;
    }


    @Override
    public Question remove(String question, String answer) {
        Question questionGenerated = new Question(question, answer);

        if (!questions.contains(questionGenerated)) {
            throw new QuestionIsNotExistException(question,answer);
        }
        questions.remove(questionGenerated);
        return questionGenerated;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {

        return questions.toArray(new Question[0])[random.nextInt(questions.size())];
    }

}
