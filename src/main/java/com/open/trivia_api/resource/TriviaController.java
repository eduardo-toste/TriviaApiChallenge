package com.open.trivia_api.resource;

import com.open.trivia_api.dto.AnswerRequest;
import com.open.trivia_api.dto.AnswerResponse;
import com.open.trivia_api.dto.QuestionResponse;
import com.open.trivia_api.usecase.TriviaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TriviaController {

    private final TriviaUseCase triviaUseCase;

    @GetMapping("/trivia/question")
    public ResponseEntity<QuestionResponse> getQuestion() {
        return ResponseEntity.ok(triviaUseCase.getQuestion());
    }

    @PostMapping("/trivia/answer/{questionId}")
    public ResponseEntity<AnswerResponse> answerQuestion(@PathVariable UUID questionId, @RequestBody AnswerRequest answer) {
        var response = triviaUseCase.answerQuestion(questionId, answer);
        return ResponseEntity.ok(response);
    }

}
