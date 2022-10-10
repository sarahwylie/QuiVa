package com.cooksys.quiz_api.controllers;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.services.QuizService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public List<QuizResponseDto> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    // TODO: Implement the remaining 6 endpoints from the documentation.

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuizResponseDto createQuiz(@RequestBody QuizRequestDto quizRequestDto) {
        return quizService.createQuiz(quizRequestDto);
    }

    @DeleteMapping("/{id}")
    public QuizResponseDto deleteQuiz(@PathVariable Long id) {
        return quizService.deleteById(id);
    }

    @PatchMapping("/{id}/rename/{newName}")
    public QuizResponseDto updateQuizName(@PathVariable Long id,
                                          @PathVariable String newName) {
        return quizService.updateQuizName(id, newName);
    }

    @GetMapping("/{id}/random")
    public QuizResponseDto getQuizById(@PathVariable Long id) {
        return quizService.getQuizById(id);
    }

    @PatchMapping("/{id}/add")
    public QuizResponseDto addQuizQuestion(@RequestBody QuestionRequestDto questionRequestDto, @PathVariable Long id) {
        return quizService.addQuizQuestion(id, questionRequestDto);
    }

    @DeleteMapping("/{id}/delete/{questionID}")
    public QuizResponseDto deleteQuizQuestion(@PathVariable Long id, @PathVariable Long questionID) throws NotFoundException {
        return quizService.deleteQuizQuestion(id, questionID);
    }
}
