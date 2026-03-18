package com.open.trivia_api.usecase;

import com.open.trivia_api.dto.AnswerRequest;
import com.open.trivia_api.dto.AnswerResponse;
import com.open.trivia_api.dto.QuestionResponse;

import java.util.UUID;

public interface TriviaUseCase {

    QuestionResponse getQuestion();
    AnswerResponse answerQuestion(UUID questionId, AnswerRequest answer);

}
