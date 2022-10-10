package com.cooksys.quiz_api.services;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import javassist.NotFoundException;

public interface QuizService {

	List<QuizResponseDto> getAllQuizzes();

	QuizResponseDto createQuiz(QuizRequestDto quizRequestDto);

	QuizResponseDto deleteById(Long id);

	QuizResponseDto updateQuizName(Long id, String newName);

	QuizResponseDto getQuizById(Long id);

	QuizResponseDto deleteQuizQuestion(Long id, Long questionID) throws NotFoundException;

	QuizResponseDto addQuizQuestion(Long id, QuestionRequestDto questionRequestDto);

}
