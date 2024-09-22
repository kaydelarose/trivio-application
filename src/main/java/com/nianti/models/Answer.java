package com.nianti.models;

public class Answer
{
    private int answerId;
    private int questionId;
    private String answerText;
    private boolean isCorrect;

    // Declare constructors:
    public Answer()
    {}

    public Answer(int answerId, int questionId, String answerText, boolean isCorrect)
    {
        this.answerId = answerId;
        this.questionId = questionId;
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }

    // Declare getters and setters:

    public int getAnswerId()
    {
        return answerId;
    }

    public void setAnswerId(int answerId)
    {
        this.answerId = answerId;
    }

    public int getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(int questionId)
    {
        this.questionId = questionId;
    }

    public String getAnswerText()
    {
        return answerText;
    }

    public void setAnswerText(String answerText)
    {
        this.answerText = answerText;
    }

    public boolean isCorrect()
    {
        return isCorrect;
    }

    public void setCorrect(boolean correct)
    {
        isCorrect = correct;
    }
}
