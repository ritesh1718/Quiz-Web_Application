// Confirm before deleting quiz or question
function confirmDelete(message) {
    return confirm(message || "Are you sure you want to delete?");
}

// Validate quiz creation form
function validateQuizForm() {
    const title = document.querySelector("input[name='title']");
    if (!title || title.value.trim() === "") {
        alert("Quiz title cannot be empty!");
        return false;
    }
    return true;
}

// Validate question creation form
function validateQuestionForm() {
    const questionText = document.querySelector("textarea[name='questionText']");
    if (!questionText || questionText.value.trim() === "") {
        alert("Question text cannot be empty!");
        return false;
    }
    return true;
}

// Add new option dynamically when creating/editing question
function addOptionField() {
    const optionsContainer = document.getElementById("options-container");

    const div = document.createElement("div");
    div.className = "option-item";

    div.innerHTML = `
        <input type="text" name="optionText" placeholder="Enter option text" required />
        <label>
            <input type="checkbox" name="isCorrect" /> Correct
        </label>
        <button type="button" class="remove-btn" onclick="this.parentElement.remove()">Remove</button>
    `;

    optionsContainer.appendChild(div);
}

// Add event listener for quiz delete buttons
document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".delete-btn").forEach(btn => {
        btn.addEventListener("click", function (e) {
            if (!confirmDelete("Do you really want to delete this item?")) {
                e.preventDefault();
            }
        });
    });
});
