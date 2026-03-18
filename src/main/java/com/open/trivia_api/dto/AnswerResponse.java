package com.open.trivia_api.dto;

public record AnswerResponse(

        boolean correct,
        String question,
        String correctAnswer,
        String userAnswer,
        String message

) {
}
