package com.open.trivia_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.open.trivia_api.domain.TriviaApiQuestion;

import java.util.List;

public record TriviaApiResponse(

        @JsonProperty("response_code")
        Integer responseCode,

        @JsonProperty("results")
        List<TriviaApiQuestion> results

) {
}
