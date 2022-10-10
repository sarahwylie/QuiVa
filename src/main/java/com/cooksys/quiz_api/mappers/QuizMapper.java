package com.cooksys.quiz_api.mappers;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuizRequestDto;
import org.mapstruct.Mapper;

import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Quiz;

@Mapper(componentModel = "spring", uses = { QuestionMapper.class })
public interface QuizMapper {

	QuizResponseDto entityToResponseDto(Quiz quiz);

	Quiz requestDtoToEntity(QuizRequestDto quizRequestDto);

	List<QuizResponseDto> entityToResponseDtos(List<Quiz> quizzes);

}
