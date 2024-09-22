let currentQuestionId = 1;  // Start with question 1
let totalQuestions = 0;  // Initialize as 0 and update dynamically
let correctAnswers = 0;

document.addEventListener("DOMContentLoaded", () => {
    startQuiz();
});

// Function to start the quiz
function startQuiz() {
    const startButton = document.getElementById("startButton");
    const quizId = startButton.getAttribute("data-quiz-id");  // Extract quizId from the button

    // Fetch the total number of questions before starting the quiz
    fetchTotalQuestions(quizId)
        .then(() => {
            startButton.addEventListener("click", () => {
                loadQuestion(quizId, currentQuestionId);
                startButton.style.display = "none"; // Load the first question when the button is clicked
            });
        });
}

// Function to fetch total questions and set the totalQuestions variable
function fetchTotalQuestions(quizId) {
    return fetch(`/api/quiz/${quizId}/total-questions`)  // Assuming a new endpoint returning total number of questions
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch total questions');
            }
            return response.json();  // Expecting a JSON response with totalQuestions
        })
        .then(data => {
            totalQuestions = data.totalQuestions;  // Assuming the response is { totalQuestions: X }
            console.log("Total Questions: ", totalQuestions);  // For debugging
        })
        .catch(error => console.error('Error fetching total questions:', error));
}

function loadQuestion(quizId, questionId) {
    fetch(`/quiz/${quizId}/question/${questionId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to load question');
            }
            return response.text();  // Fetch the Thymeleaf HTML fragment
        })
        .then(html => {
            const quizText = document.getElementById("quizText");
            quizText.innerHTML = html;  // Inject the question fragment into the DOM

            // Re-attach event listeners for answer buttons and the next button
            attachAnswerListeners(quizId);
        })
        .catch(error => console.error('Error:', error));
}

function attachAnswerListeners(quizId) {
    const nextButton = document.getElementById("nextButton");

    // Attach change event to all radio buttons
    document.querySelectorAll('input[name="answer"]').forEach((radio) => {
        radio.addEventListener('change', () => {
            // Show the "Next" button when an answer is selected
            nextButton.style.display = "block";
        });
    });

    // Attach click event to the "Next" button
    nextButton.addEventListener('click', () => {
        const selectedAnswer = document.querySelector('input[name="answer"]:checked');
        if (selectedAnswer) {
            const selectedAnswerId = selectedAnswer.value;
            const correctAnswer = selectedAnswer.getAttribute('data-is-correct');

            selectAnswer(correctAnswer);
            loadNextQuestion(quizId);
        } else {
            alert("Please select an answer!");  // Just in case no answer is selected
        }
    });
}


function loadNextQuestion(quizId) {
    console.log("Current question ID: ", currentQuestionId);
    console.log("Total questions: ", totalQuestions);

    // Load the next question if there are more
    if (currentQuestionId < totalQuestions) {
        currentQuestionId++;
        loadQuestion(quizId, currentQuestionId);
    } else {
        // Show final message or score when the quiz is finished
        showFinalMessage();
    }
}

function selectAnswer(correctAnswerId) {
    // Compare selected answer with the correct answer
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
