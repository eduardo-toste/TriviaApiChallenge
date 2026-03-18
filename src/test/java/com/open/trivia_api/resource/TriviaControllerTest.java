package com.open.trivia_api.resource;

import com.open.trivia_api.dto.AnswerRequest;
import com.open.trivia_api.dto.AnswerResponse;
import com.open.trivia_api.dto.QuestionResponse;
import com.open.trivia_api.usecase.TriviaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TriviaControllerTest {

    private TriviaController triviaController;

    @Mock
    private TriviaUseCase triviaUseCase;

    private final UUID questionId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        triviaController = new TriviaController(triviaUseCase);
    }

    @Test
    void testGetQuestionSuccess() {
        QuestionResponse response = new QuestionResponse(
                questionId,
                "boolean",
                "medium",
                "General Knowledge",
                "Is the Earth flat?"
        );

        when(triviaUseCase.getQuestion()).thenReturn(response);

        ResponseEntity<QuestionResponse> result = triviaController.getQuestion();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(questionId, result.getBody().id());
        assertEquals("boolean", result.getBody().type());
        assertEquals("medium", result.getBody().difficulty());
        assertEquals("General Knowledge", result.getBody().category());
        assertEquals("Is the Earth flat?", result.getBody().question());
    }

    @Test
    void testAnswerQuestionCorrect() {
        AnswerRequest request = new AnswerRequest(false);
        AnswerResponse response = new AnswerResponse(
                true,
                "Is the Earth flat?",
                "False",
                "false",
                "Correct!"
        );

        when(triviaUseCase.answerQuestion(eq(questionId), any(AnswerRequest.class)))
                .thenReturn(response);

        ResponseEntity<AnswerResponse> result = triviaController.answerQuestion(questionId, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().correct());
        assertEquals("Correct!", result.getBody().message());
        assertEquals("False", result.getBody().correctAnswer());
        assertEquals("false", result.getBody().userAnswer());
    }

    @Test
    void testAnswerQuestionIncorrect() {
        AnswerRequest request = new AnswerRequest(true);
        AnswerResponse response = new AnswerResponse(
                false,
                "Is the Earth flat?",
                "False",
                "true",
                "Wrong!"
        );

        when(triviaUseCase.answerQuestion(eq(questionId), any(AnswerRequest.class)))
                .thenReturn(response);

        ResponseEntity<AnswerResponse> result = triviaController.answerQuestion(questionId, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertFalse(result.getBody().correct());
        assertEquals("Wrong!", result.getBody().message());
        assertEquals("False", result.getBody().correctAnswer());
        assertEquals("true", result.getBody().userAnswer());
    }

    @Test
    void testResponseStatus() {
        QuestionResponse response = new QuestionResponse(
                questionId,
                "boolean",
                "medium",
                "General Knowledge",
                "Test question?"
        );

        when(triviaUseCase.getQuestion()).thenReturn(response);

        ResponseEntity<QuestionResponse> result = triviaController.getQuestion();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

}
