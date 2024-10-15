package com.nianti.services;

import com.nianti.models.Answer;
import com.nianti.models.Question;
import com.nianti.models.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDao
{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionDao(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Question> getQuestionsByQuizId(int quizId)
    {
        List<Question> questions = new ArrayList<>();
        String sql = """
            SELECT question_id, quiz_id, question_number, question_text
            FROM question
            WHERE quiz_id = ?;
        """;
        var row = jdbcTemplate.queryForRowSet(sql, quizId);

        while (row.next()) {
            Question question = mapRowToQuestion(row);

            List<Answer> answers = getAnswersByQuestionId(question.getQuestionId());
            question.setAnswers(answers);  

            questions.add(question);
        }
        return questions;
    }

    public Question getQuestion(int quizId, int questionNumber)
    {
        String sql = """
            SELECT question_id, quiz_id, question_number, question_text
            FROM question
            WHERE quiz_id = ? AND question_number = ?
        """;

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, quizId, questionNumber);

        if (rowSet.next()) {
            Question question = mapRowToQuestion(rowSet);

            List<Answer> answers = getAnswersByQuestionId(question.getQuestionId());
            question.setAnswers(answers);  

            return question;
        }

        return null;
    }

    private Question mapRowToQuestion(SqlRowSet row)
    {
        int id = row.getInt("question_id");
        int quiz = row.getInt("quiz_id");
        int number = row.getInt("question_number");
        String text = row.getString("question_text");

        return new Question(id, quiz, number, text);
    }

    public List<Answer> getAnswersByQuestionId(int questionId)
    {
        String sql = """
            SELECT answer_id, question_id, answer_text, is_correct
            FROM answer
            WHERE question_id = ?;
        """;

        List<Answer> answers = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, questionId);

        while (rowSet.next())
        {
            Answer answer = new Answer();
            answer.setAnswerId(rowSet.getInt("answer_id"));
            answer.setQuestionId(rowSet.getInt("question_id"));
            answer.setAnswerText(rowSet.getString("answer_text"));
            answer.setCorrect(rowSet.getBoolean("is_correct"));
            answers.add(answer);
        }

        return answers;
    }

    public int getTotalQuestionsByQuizId(int quizId)
    {
        String sql = "SELECT COUNT(*) FROM question WHERE quiz_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, quizId);
    }


    public void deleteQuestion(int questionId)
    {

        String deleteAnswersSql = "DELETE FROM answer WHERE question_id = ?";
        jdbcTemplate.update(deleteAnswersSql, questionId);

        String deleteQuestionSql = "DELETE FROM question WHERE question_id = ?";
        jdbcTemplate.update(deleteQuestionSql, questionId);
    }

    public void addQuestion(Question question)
    {
        String sql = """
            INSERT INTO question (quiz_id, question_number, question_text)
            VALUES (?, ?, ?);
        """;

        jdbcTemplate.update(sql, question.getQuizId(), question.getQuestionNumber(), question.getQuestionText());

            for (Answer answer : getAnswersByQuestionId(question.getQuestionId()))
            {
                addAnswer(answer, question.getQuestionId());
            }
    }

    public void addAnswer(Answer answer, int questionId)
    {
        String sql = """
            INSERT INTO answer (question_id, answer_text, is_correct)
            VALUES (?, ?, ?);
        """;

        jdbcTemplate.update(sql, questionId, answer.getAnswerText(), answer.isCorrect());
    }
}


