package com.nianti.controllers;

import com.nianti.services.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Create controller for managing homepage and loading list of quizzes:
@Controller
public class HomeController
{
    @Autowired
    private QuizDao quizDao;

    @GetMapping("/")
    public String index(Model model)
    {
        var quizzes = quizDao.getAllQuizzes();

        model.addAttribute("title", "Trivio Home");
        model.addAttribute("quizzes", quizzes);

        return "index";
    }
}
