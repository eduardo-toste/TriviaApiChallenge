package com.open.trivia_api.dto;

import java.util.UUID;

public record QuestionAnswerResponse(

        UUID questionId,
        boolean correct,
        String question,
        String correctAnswer,
        String userAnswer,
        String message

) {
}
