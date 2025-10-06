// Show flash messages and auto-hide after few seconds
document.addEventListener("DOMContentLoaded", function () {
    const flashMessage = document.querySelector(".flash-message");
    if (flashMessage) {
        setTimeout(() => {
            flashMessage.style.display = "none";
        }, 4000);
    }
});

// Validate login form
function validateLoginForm() {
    const username = document.querySelector("input[name='username']");
    const password = document.querySelector("input[name='password']");

    if (!username || username.value.trim() === "") {
        alert("Username cannot be empty!");
        return false;
    }
    if (!password || password.value.trim() === "") {
        alert("Password cannot be empty!");
        return false;
    }
    return true;
}

// Validate registration form
function validateRegisterForm() {
    const username = document.querySelector("input[name='username']");
    const email = document.querySelector("input[name='email']");
    const password = document.querySelector("input[name='password']");
    const confirmPassword = document.querySelector("input[name='confirmPassword']");

    if (!username.value.trim() || !email.value.trim() || !password.value.trim()) {
        alert("All fields are required!");
        return false;
    }

    if (password.value !== confirmPassword.value) {
        alert("Passwords do not match!");
        return false;
    }
    return true;
}
