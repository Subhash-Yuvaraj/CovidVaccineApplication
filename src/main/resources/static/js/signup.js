// Validate the signup form on submission
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
    var nameInput = document.getElementById("name");
    var dobInput = document.getElementById("dob");
    var address1Input = document.getElementById("address1");
    var address2Input = document.getElementById("address2");
    var pinInput = document.getElementById("pin");

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

    // Validate name (not empty)
    if (nameInput.value.trim() === "") {
        showError(nameInput, "Name is required");
        return false;
    }

    // Validate date of birth (not empty)
    if (dobInput.value.trim() === "") {
        showError(dobInput, "Date of Birth is required");
        return false;
    }

    // Validate address line 1 (not empty)
    if (address1Input.value.trim() === "") {
        showError(address1Input, "Address Line 1 is required");
        return false;
    }

    // Validate address line 2 (not empty)
    if (address2Input.value.trim() === "") {
        showError(address2Input, "Address Line 2 is required");
        return false;
    }

    // Validate PIN (numeric and not empty)
    if (isNaN(pinInput.value.trim()) || pinInput.value.trim() === "") {
        showError(pinInput, "PIN must be a numeric value");
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
