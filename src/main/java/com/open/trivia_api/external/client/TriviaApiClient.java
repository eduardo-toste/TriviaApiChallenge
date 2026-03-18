package com.open.trivia_api.external.client;

import com.open.trivia_api.domain.TriviaApiQuestion;
import com.open.trivia_api.dto.TriviaApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "triviaApiClient", url = "https://opentdb.com/api.php")
public interface TriviaApiClient {

    @GetMapping(value = "?amount=01&category=9&difficulty=medium&type=boolean", consumes = MediaType.APPLICATION_JSON_VALUE)
    TriviaApiResponse getQuestion();

}
