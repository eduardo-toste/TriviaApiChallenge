package com.open.trivia_api.resource;

import com.open.trivia_api.dto.AnswerRequest;
import com.open.trivia_api.dto.AnswerResponse;
import com.open.trivia_api.dto.QuestionResponse;
import com.open.trivia_api.usecase.TriviaUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Trivia", description = "Endpoints para jogar trivia")
public class TriviaController {

    private final TriviaUseCase triviaUseCase;

    @GetMapping("/trivia/question")
    @Operation(summary = "Obter uma pergunta", description = "Retorna uma nova pergunta de trivia")
    public ResponseEntity<QuestionResponse> getQuestion() {
        return ResponseEntity.ok(triviaUseCase.getQuestion());
    }

    @PostMapping("/trivia/answer/{questionId}")
    @Operation(summary = "Responder uma pergunta", description = "Valida a resposta para uma pergunta de trivia")
    public ResponseEntity<AnswerResponse> answerQuestion(@PathVariable UUID questionId, @RequestBody AnswerRequest answer) {
        var response = triviaUseCase.answerQuestion(questionId, answer);
        return ResponseEntity.ok(response);
    }

}
