// admin-login.js

window.onload = function() {
  // Add event listener to form submit
  var form = document.getElementById("login-form");
  form.addEventListener("submit", validateForm);
};

// Validate form before submission
function validateForm(event) {
  event.preventDefault();
  
  var emailInput = document.getElementById("email");
  var passwordInput = document.getElementById("password");

  var errorDiv = document.getElementById("error-message");
  errorDiv.innerText = "";

  if (emailInput.value.trim() === "") {
    displayErrorMessage("Please enter your email.");
    return;
  }

  if (passwordInput.value.trim() === "") {
    displayErrorMessage("Please enter your password.");
    return;
  }

  event.target.submit();
}

// Display error message
function displayErrorMessage(message) {
  var errorDiv = document.getElementById("error-message");
  errorDiv.innerText = message;
}
