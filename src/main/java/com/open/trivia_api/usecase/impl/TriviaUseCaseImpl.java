package com.open.trivia_api.usecase.impl;

import com.open.trivia_api.domain.TriviaApiQuestion;
import com.open.trivia_api.dto.AnswerRequest;
import com.open.trivia_api.dto.AnswerResponse;
import com.open.trivia_api.dto.QuestionResponse;
import com.open.trivia_api.dto.TriviaApiResponse;
import com.open.trivia_api.external.client.TriviaApiClient;
import com.open.trivia_api.usecase.TriviaUseCase;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class TriviaUseCaseImpl implements TriviaUseCase {

    private final TriviaApiClient client;
    private final HashMap<UUID, TriviaApiQuestion> db = new HashMap<>();

    public TriviaUseCaseImpl(TriviaApiClient client) {
        this.client = client;
    }

    @Override
    public QuestionResponse getQuestion() {
        TriviaApiResponse response = client.getQuestion();
        TriviaApiQuestion question = response.results().get(0);

        UUID id = UUID.randomUUID();

        db.put(id, question);
        return new QuestionResponse(
                id,
                question.getType(),
                question.getDifficulty(),
                question.getCategory(),
                question.getQuestion()
        );
    }

    @Override
    public AnswerResponse answerQuestion(UUID questionId, AnswerRequest request) {
        var selectedQuestion = db.get(questionId);

        if (selectedQuestion == null) throw new RuntimeException("Question not found!");

        boolean isCorrect = request.answer().equals(Boolean.parseBoolean(selectedQuestion.getCorrectAnswer()));

        return new AnswerResponse(
                isCorrect,
                selectedQuestion.getQuestion(),
                selectedQuestion.getCorrectAnswer(),
                String.valueOf(request.answer()),
                isCorrect ? "Correct!" : "Wrong!");
    }

}
