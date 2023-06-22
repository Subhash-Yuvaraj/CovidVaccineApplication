// Validate the login form on submission
document.querySelector("form").addEventListener("submit", function (event) {
    event.preventDefault(); // Prevent form submission
    
    // Perform form validation
    if (validateForm()) {
        this.submit(); // Submit the form if it's valid
    }
});

// Form validation function
function validateForm() {
    var emailInput = document.getElementById("email");
    var passwordInput = document.getElementById("password");

    // Validate email
    if (!validateEmail(emailInput.value)) {
        showError(emailInput, "Invalid email address");
        return false;
    }

    // Validate password (minimum 6 characters)
    if (passwordInput.value.length < 6) {
        showError(passwordInput, "Password must be at least 6 characters long");
        return false;
    }

    // If all validations pass, return true
    return true;
}

// Email validation function
function validateEmail(email) {
    // Use a regular expression for basic email validation
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

// Display error message for an input field
function showError(inputElement, errorMessage) {
    var errorSpan = inputElement.parentElement.querySelector(".error");
    errorSpan.innerText = errorMessage;
}
