package com.nianti.models;

import java.util.ArrayList;
import java.util.List;

public class Question
{
    private int questionId;
    private int quizId;
    private int questionNumber;
    private String questionText;

    private List<Answer> answers = new ArrayList<>();  

    public Question()
    {
        this.answers = new ArrayList<>();
    }

    public Question(int questionId, int quizId, int questionNumber, String questionText)
    {
        this.questionId = questionId;
        this.quizId = quizId;
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.answers = new ArrayList<>();
    }

    public int getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(int questionId)
    {
        this.questionId = questionId;
    }

    public int getQuizId()
    {
        return quizId;
    }

    public void setQuizId(int quizId)
    {
        this.quizId = quizId;
    }

    public int getQuestionNumber()
    {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber)
    {
        this.questionNumber = questionNumber;
    }

    public String getQuestionText()
    {
        return questionText;
    }

    public void setQuestionText(String questionText)
    {
        this.questionText = questionText;
    }

    public List<Answer> getAnswers()
    {
        return answers;
    }

    public void setAnswers(List<Answer> answers)
    {
        this.answers = answers;
    }
}
