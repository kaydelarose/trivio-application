package com.nianti.services;

import com.nianti.models.Answer;
import com.nianti.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerDao
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AnswerDao(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Answer> getAnswersByQuestionId(int questionId)
    {
        String sql = """
            SELECT answer_id
            , question_id
            , answer_text
            , is_correct
            FROM answer
            WHERE question_id = ?;
        """;

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, questionId);
        List<Answer> answers = new ArrayList<>();

        while (rowSet.next()) {
            Answer answer = mapRowToAnswer(rowSet);
            answers.add(answer);
        }

        return answers;
    }

    private Answer mapRowToAnswer(SqlRowSet rowSet) {
        Answer answer = new Answer();
        answer.setAnswerId(rowSet.getInt("answer_id"));
        answer.setQuestionId(rowSet.getInt("question_id"));
        answer.setAnswerText(rowSet.getString("answer_text"));
        answer.setCorrect(rowSet.getBoolean("is_correct"));
        return answer;
    }
}

