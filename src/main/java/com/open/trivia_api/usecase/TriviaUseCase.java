package com.open.trivia_api.usecase;

import com.open.trivia_api.dto.QuestionAnswerRequest;
import com.open.trivia_api.dto.QuestionAnswerResponse;
import com.open.trivia_api.domain.TriviaApiQuestion;
import com.open.trivia_api.dto.QuestionResponse;

import java.util.UUID;

public interface TriviaUseCase {

    QuestionResponse getQuestion();
    QuestionAnswerResponse answerQuestion(UUID questionId, QuestionAnswerRequest answer);

}
