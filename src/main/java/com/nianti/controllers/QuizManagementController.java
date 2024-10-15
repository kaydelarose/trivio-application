package com.nianti.controllers;

import com.nianti.models.Question;
import com.nianti.models.Quiz;
import com.nianti.services.QuestionDao;
import com.nianti.services.QuizDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/quizzes")
public class QuizManagementController
{

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;

    @GetMapping
    public String showQuizManagementPage(Model model)
    {
        List<Quiz> quizzes = quizDao.getAllQuizzes();
        model.addAttribute("quizzes", quizzes);
        return "quiz-management/index"; 
    }

    @GetMapping("/add")
    public String showNewQuizForm(Model model)
    {
        model.addAttribute("quiz", new Quiz());  
        return "quiz-management/new"; 
    }

    @PostMapping("/add")
    public String addNewQuiz(@Valid @ModelAttribute("quiz") Quiz quiz, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            return "quiz-management/new";  
        }

        quizDao.addQuiz(quiz); 
        return "quiz-management/add_success";
    }

    @PostMapping("/toggle-live/{quizId}")
    public String toggleQuizLiveStatus(@PathVariable int quizId, RedirectAttributes redirectAttributes)
    {

        Quiz quiz = quizDao.getQuizById(quizId);

        if (quiz != null)
        {

            boolean isCurrentlyLive = quiz.isLive();
            quiz.setLive(!isCurrentlyLive);

            quizDao.editQuiz(quiz);

            String message = isCurrentlyLive ? "The quiz has been turned off." : "The quiz has been turned on.";
            redirectAttributes.addFlashAttribute("message", message);
        }

        return "redirect:/quizzes";
    }

    @GetMapping("/{quizId}")
    public String detailsPage(@PathVariable int quizId, Model model)
    {

        Quiz quiz = quizDao.getQuizById(quizId);
        List<Question> questions = questionDao.getQuestionsByQuizId(quizId);

        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questions);

        return "quiz-management/details";  
    }
    
    @PostMapping("/{quizId}/questions/delete/{questionId}")
    public String deleteQuestion(@PathVariable int quizId, @PathVariable int questionId, @RequestParam(required = false) String returnUrl, RedirectAttributes redirectAttributes)
    {
        questionDao.deleteQuestion(questionId);  
        redirectAttributes.addFlashAttribute("message", "Question deleted successfully.");

        if (returnUrl != null) {
            return "redirect:" + returnUrl;
        }

        return "redirect:/quizzes/" + quizId;
    }
}




