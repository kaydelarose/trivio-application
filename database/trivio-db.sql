DROP DATABASE IF EXISTS trivio;

CREATE DATABASE trivio;

USE trivio;

CREATE TABLE quiz
(
    quiz_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    quiz_title VARCHAR(255),
    is_live BOOLEAN DEFAULT 0
);

CREATE TABLE question
(
    question_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    quiz_id INT NOT NULL REFERENCES quiz(quiz_id),
    question_number INT NOT NULL,
    question_text VARCHAR(2000) NOT NULL
);

CREATE TABLE answer
(
    answer_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    question_id INT NOT NULL REFERENCES question(question_id),
    answer_text VARCHAR(2000) NOT NULL,
    is_correct BOOLEAN NOT NULL DEFAULT 0
);

-- add quiz
INSERT INTO quiz(quiz_id, quiz_title, is_live)
VALUES (1, 'General History', 1)
     , (2, 'Academic Trivia', 1)
;

INSERT INTO question(question_id, quiz_id, question_number, question_text)
VALUES (1, 1, 1, 'Who was the first President of the United States?')
     , (2, 1, 2, 'What year did the Titanic sink?')
     , (3, 1, 3, 'Which empire was famously led by Genghis Khan?')
     , (4, 1, 4, 'The fall of the Berlin Wall occurred in which year?')
     , (5, 1, 5, 'Who was the British Prime Minister during World War II?')
     , (6, 2, 1, 'What is the chemical symbol for gold?')
     , (7, 2, 2, 'In which year did the United States declare its independence?')
     , (8, 2, 3, 'Who wrote the play Romeo and Juliet?')
     , (9, 2, 4, 'What is the formula for the area of a circle?')
     , (10, 2, 5, 'What is the powerhouse of the cell?')
;

INSERT INTO answer(question_id, answer_text, is_correct)
VALUES (1, 'Thomas Jefferson', 0)
     , (1, 'George Washington', 1)
     , (1, 'Abraham Lincoln', 0)
     , (1, 'John Adams', 0)

     , (2, '1912', 1)
     , (2, '1905', 0)
     , (2, '1923', 0)
     , (2, '1915', 0)

     , (3, 'Ottoman Empire', 0)
     , (3, 'Roman Empire', 0)
     , (3, 'Mongol Empire', 1)
     , (3, 'Persian Empire', 0)

     , (4, '1979', 0)
     , (4, '1985', 0)
     , (4, '1989', 1)
     , (4, '1991', 0)

     , (5, 'Neville Chamberlain', 0)
     , (5, 'Winston Churchill', 1)
     , (5, 'Margaret Thatcher', 0)
     , (5, 'Harold Wilson', 0)

     , (6, 'Au', 1)
     , (6, 'Ag', 0)
     , (6, 'Fe', 0)
     , (6, 'Pb', 0)

     , (7, '1776', 1)
     , (7, '1789', 0)
     , (7, '1801', 0)
     , (7, '1812', 0)

     , (8, 'Charles Dickens', 0)
     , (8, 'William Shakespeare', 1)
     , (8, 'Mark Twain', 0)
     , (8, 'Jane Austen', 0)

     , (9, '2πr', 0)
     , (9, 'πd', 0)
     , (9, 'πr²', 1)
     , (9, '2πd', 0)

     , (10, 'Nucleus', 0)
     , (10, 'Ribosome', 0)
     , (10, 'Endoplasmic reticulum', 0)
     , (10, 'Mitochondria', 1)
;
