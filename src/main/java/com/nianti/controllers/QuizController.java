package com.nianti.controllers;

import com.nianti.models.Question;
import com.nianti.services.QuestionDao;
import com.nianti.models.Quiz;
import com.nianti.services.QuizDao;
import com.nianti.services.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
public class QuizController
{

    @Autowired
    private QuizDao quizDao;

    @Autowired QuestionDao questionDao;

    // Display the quiz page:
    @GetMapping("/quiz/{quizId}")
    public String displayQuizById(@PathVariable int quizId, Model model)
    {

        // Get quiz details:
        Quiz quiz          = quizDao.getQuizById(quizId);
        int totalQuestions = quiz.getQuestions().size();

        model.addAttribute("totalQuestions", totalQuestions);
        model.addAttribute("quiz", quiz);

        return "quiz/index";

    }

    // Load the given question for the given quiz:
    @GetMapping("/quiz/{quizId}/question/{questionId}")
    public String loadQuestion(@PathVariable int quizId, @PathVariable int questionId, Model model)
    {
        Question question = questionDao.getQuestion(quizId, questionId);

        model.addAttribute("question", question);

        return "quiz/fragments/currentQuestion";
    }

}
