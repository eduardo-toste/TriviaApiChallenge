package com.open.trivia_api.usecase.impl;

import com.open.trivia_api.domain.TriviaApiQuestion;
import com.open.trivia_api.dto.AnswerRequest;
import com.open.trivia_api.dto.AnswerResponse;
import com.open.trivia_api.dto.QuestionResponse;
import com.open.trivia_api.dto.TriviaApiResponse;
import com.open.trivia_api.external.client.TriviaApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TriviaUseCaseImplTest {

    private TriviaUseCaseImpl triviaUseCaseImpl;

    @Mock
    private TriviaApiClient triviaApiClient;

    private TriviaApiQuestion triviaApiQuestion;
    private TriviaApiResponse triviaApiResponse;

    @BeforeEach
    void setUp() {
        triviaApiQuestion = new TriviaApiQuestion(
                "boolean",
                "medium",
                "General Knowledge",
                "Is the Earth flat?",
                "False"
        );

        triviaApiResponse = new TriviaApiResponse(0, List.of(triviaApiQuestion));

        triviaUseCaseImpl = new TriviaUseCaseImpl(triviaApiClient);
    }

    @Test
    void testGetQuestionSuccess() {
        when(triviaApiClient.getQuestion()).thenReturn(triviaApiResponse);

        QuestionResponse response = triviaUseCaseImpl.getQuestion();

        assertNotNull(response);
        assertNotNull(response.id());
        assertEquals("boolean", response.type());
        assertEquals("medium", response.difficulty());
        assertEquals("General Knowledge", response.category());
        assertEquals("Is the Earth flat?", response.question());
    }

    @Test
    void testAnswerQuestionCorrect() {
        when(triviaApiClient.getQuestion()).thenReturn(triviaApiResponse);

        QuestionResponse questionResponse = triviaUseCaseImpl.getQuestion();
        UUID questionId = questionResponse.id();

        AnswerRequest request = new AnswerRequest(false);
        AnswerResponse response = triviaUseCaseImpl.answerQuestion(questionId, request);

        assertTrue(response.correct());
        assertEquals("Correct!", response.message());
        assertEquals("false", response.userAnswer());
        assertEquals("False", response.correctAnswer());
    }

    @Test
    void testAnswerQuestionIncorrect() {
        when(triviaApiClient.getQuestion()).thenReturn(triviaApiResponse);

        QuestionResponse questionResponse = triviaUseCaseImpl.getQuestion();
        UUID questionId = questionResponse.id();

        AnswerRequest request = new AnswerRequest(true);
        AnswerResponse response = triviaUseCaseImpl.answerQuestion(questionId, request);

        assertFalse(response.correct());
        assertEquals("Wrong!", response.message());
        assertEquals("true", response.userAnswer());
        assertEquals("False", response.correctAnswer());
    }

    @Test
    void testAnswerQuestionNotFound() {
        UUID invalidId = UUID.randomUUID();
        AnswerRequest request = new AnswerRequest(false);

        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> triviaUseCaseImpl.answerQuestion(invalidId, request));

        assertEquals("Question not found!", exception.getMessage());
    }

}