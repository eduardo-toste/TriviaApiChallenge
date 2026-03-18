package com.open.trivia_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record QuestionResponse(

        UUID id,
        String type,
        String difficulty,
        String category,
        String question

) {
}
