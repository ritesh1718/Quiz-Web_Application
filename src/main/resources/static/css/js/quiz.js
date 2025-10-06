let currentQuestionIndex = 0;
let score = 0;
let questions = [];

// Load quiz questions from DOM or API
function loadQuiz(quizData) {
    questions = quizData;
    currentQuestionIndex = 0;
    score = 0;
    showQuestion();
}

// Show question
function showQuestion() {
    if (currentQuestionIndex >= questions.length) {
        showResults();
        return;
    }

    const q = questions[currentQuestionIndex];
    const questionContainer = document.getElementById("quiz-question");
    const optionsContainer = document.getElementById("quiz-options");

    questionContainer.textContent = q.questionText;
    optionsContainer.innerHTML = "";

    q.answerOptions.forEach((opt, index) => {
        const optionElement = document.createElement("div");
        optionElement.className = "option";
        optionElement.innerHTML = `
            <label>
                <input type="radio" name="answer" value="${opt.id}" />
                ${opt.optionText}
            </label>
        `;
        optionsContainer.appendChild(optionElement);
    });
}

// Submit answer
function submitAnswer() {
    const selected = document.querySelector("input[name='answer']:checked");
    if (!selected) {
        alert("Please select an option!");
        return;
    }

    const q = questions[currentQuestionIndex];
    const selectedId = parseInt(selected.value);

    const correctOption = q.answerOptions.find(opt => opt.correct);

    if (correctOption && correctOption.id === selectedId) {
        score++;
        alert("✅ Correct!");
    } else {
        alert("❌ Incorrect! Correct answer: " + (correctOption ? correctOption.optionText : "N/A"));
    }

    currentQuestionIndex++;
    showQuestion();
}

// Show results
function showResults() {
    const questionContainer = document.getElementById("quiz-question");
    const optionsContainer = document.getElementById("quiz-options");

    questionContainer.textContent = "🎉 Quiz Finished!";
    optionsContainer.innerHTML = `<p>Your Score: ${score} / ${questions.length}</p>`;
}
