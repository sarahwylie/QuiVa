package com.cooksys.quiz_api.services.impl;

import java.util.List;
import java.util.Optional;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.mappers.QuestionMapper;
import com.cooksys.quiz_api.repositories.QuestionRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.mappers.QuizMapper;
import com.cooksys.quiz_api.repositories.QuizRepository;
import com.cooksys.quiz_api.services.QuizService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

	private final QuizRepository quizRepository;

	private final QuestionRepository questionRepository;
	private final QuizMapper quizMapper;
	private final QuestionMapper questionMapper;


	//	private Quiz getQuiz(Long id) throws NotFoundException {
//	Optional<Quiz> optionalQuiz = quizRepository.findById(id);
//	if (optionalQuiz.isEmpty()) {
//		throw new NotFoundException("No quiz found with ID" + id);
//	}
//	return optionalQuiz.get();
//	}
//
	@Override
	public List<QuizResponseDto> getAllQuizzes() {
		return quizMapper.entityToResponseDtos(quizRepository.findAll());
	}

	@Override
	public QuizResponseDto createQuiz(QuizRequestDto quizRequestDto) {
		Quiz quizToSave = quizMapper.requestDtoToEntity(quizRequestDto);
		quizToSave.setName(quizRequestDto.getName());
		quizToSave.setQuestions(quizRequestDto.getQuestions());
		Quiz savedQuiz = quizRepository.saveAndFlush(quizToSave);
		return quizMapper.entityToResponseDto(savedQuiz);
	}

	@Override
	public QuizResponseDto deleteById(Long id) {
		Optional<Quiz> optionalQuiz = quizRepository.findById(id);
		Quiz quizToDelete = optionalQuiz.get();
		quizRepository.delete(quizToDelete);
		return quizMapper.entityToResponseDto(quizToDelete);
	}

	@Override
	public QuizResponseDto updateQuizName(Long id, String newName) {
		Optional<Quiz> optionalQuizName = quizRepository.findById(id);
		Quiz updatedQuizName = optionalQuizName.get();
		updatedQuizName.setName(newName);
		return quizMapper.entityToResponseDto(quizRepository.saveAndFlush(updatedQuizName));
	}

	@Override
	public QuizResponseDto getQuizById(Long id) {
		Optional<Quiz> optionalQuiz = quizRepository.findById(id);
		Quiz randomQuiz = optionalQuiz.get();
		return quizMapper.entityToResponseDto(randomQuiz);
	}

	@Override
	public QuizResponseDto deleteQuizQuestion(Long id, Long questionID) throws NotFoundException {
		if (id == null) {
			throw new NotFoundException("No quiz found with ID" + id);
		}
		Optional<Quiz> optionalQuiz = quizRepository.findById(id);
		Quiz questionToDelete = optionalQuiz.get();
		questionToDelete.getQuestions();
		Optional<Question> optionalQuestion = questionRepository.findById(questionID);
		if (questionID == null) {
			throw new NotFoundException("No question found with ID" + questionID);
		}
		Question questionToBeDeleted = optionalQuestion.get();
		questionRepository.delete(questionToBeDeleted);
		quizRepository.delete(questionToDelete);
		return quizMapper.entityToResponseDto(questionToDelete);
	}

	@Override
	public QuizResponseDto addQuizQuestion(Long id, QuestionRequestDto questionRequestDto) {
		Optional<Quiz> optionalQuizzle = quizRepository.findById(id);
		Quiz quizzleFoShizzle = optionalQuizzle.get();
		Question updatedQuizQuestion = questionMapper.requestDtoToEntity(questionRequestDto);
		updatedQuizQuestion.setText(questionRequestDto.getText());
		updatedQuizQuestion.setQuiz(quizzleFoShizzle);
		Question newQuestion = questionRepository.saveAndFlush(updatedQuizQuestion);
		quizzleFoShizzle.getQuestions().add(newQuestion);
		Quiz QFoShiz = quizRepository.saveAndFlush(quizzleFoShizzle);
		return quizMapper.entityToResponseDto(QFoShiz);
	}
}
