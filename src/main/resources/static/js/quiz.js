let currentQuestionId = 1; 
let totalQuestions = 0;  
let correctAnswers = 0;

document.addEventListener("DOMContentLoaded", () => {
    startQuiz();
});


function startQuiz() {
    const startButton = document.getElementById("startButton");
    const quizId = startButton.getAttribute("data-quiz-id");  


    fetchTotalQuestions(quizId)
        .then(() => {
            startButton.addEventListener("click", () => {
                loadQuestion(quizId, currentQuestionId);
                startButton.style.display = "none"; 
            });
        });
}


function fetchTotalQuestions(quizId) {
    return fetch(`/api/quiz/${quizId}/total-questions`)  
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch total questions');
            }
            return response.json(); 
        })
        .then(data => {
            totalQuestions = data.totalQuestions; 
            console.log("Total Questions: ", totalQuestions); 
        })
        .catch(error => console.error('Error fetching total questions:', error));
}

function loadQuestion(quizId, questionId) {
    fetch(`/quiz/${quizId}/question/${questionId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to load question');
            }
            return response.text(); 
        })
        .then(html => {
            const quizText = document.getElementById("quizText");
            quizText.innerHTML = html; 

            attachAnswerListeners(quizId);
        })
        .catch(error => console.error('Error:', error));
}

function attachAnswerListeners(quizId) {
    const nextButton = document.getElementById("nextButton");

    document.querySelectorAll('input[name="answer"]').forEach((radio) => {
        radio.addEventListener('change', () => {
            
            nextButton.style.display = "block";
        });
    });

    nextButton.addEventListener('click', () => {
        const selectedAnswer = document.querySelector('input[name="answer"]:checked');
        if (selectedAnswer) {
            const selectedAnswerId = selectedAnswer.value;
            const correctAnswer = selectedAnswer.getAttribute('data-is-correct');

            selectAnswer(correctAnswer);
            loadNextQuestion(quizId);
        } else {
            alert("Please select an answer!"); 
        }
    });
}


function loadNextQuestion(quizId) {
    console.log("Current question ID: ", currentQuestionId);
    console.log("Total questions: ", totalQuestions);

    if (currentQuestionId < totalQuestions) {
        currentQuestionId++;
        loadQuestion(quizId, currentQuestionId);
    } else {

        showFinalMessage();
    }
}

function selectAnswer(correctAnswerId) {
    console.log("correctAnswerId: ", correctAnswerId)
    if (correctAnswerId == "true") {
        console.log("Correct answer!");
        correctAnswers++;
        console.log("total correct: ", correctAnswers)
    } else {
        console.log(correctAnswerId)
        console.log("Incorrect answer.");
    }
}

function showFinalMessage() {
    const quizText = document.getElementById("quizText");
    quizText.innerHTML = `<h2>Thank you for participating!<br><br>Your score was: ${correctAnswers} out of ${totalQuestions}</h2>`;
}
