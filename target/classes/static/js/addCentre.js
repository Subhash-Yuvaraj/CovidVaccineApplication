// addCentre.js

document.addEventListener("DOMContentLoaded", function() {
  var form = document.getElementById("addCentreForm");
  form.addEventListener("submit", validateForm);
});

function validateForm(event) {
  var addressLine1Input = document.getElementById("addressLine1");
  var addressLine2Input = document.getElementById("addressLine2");
  var pinInput = document.getElementById("pin");
  var workingFromInput = document.getElementById("workingFrom");
  var workingToInput = document.getElementById("workingTo");
  var errorMessage = document.getElementById("error");
  var isValid = true;

  if (addressLine1Input.value.trim() === "") {
    showError("Please enter Address Line 1");
    isValid = false;
  }

  if (addressLine2Input.value.trim() === "") {
    showError("Please enter Address Line 2");
    isValid = false;
  }

  if (pinInput.value.trim() === "") {
    showError("Please enter PIN");
    isValid = false;
  }

  if (!isValidTimeFormat(workingFromInput.value)) {
    showError("Invalid Working From time format. Please use hh:mm:ss");
    isValid = false;
  }

  if (!isValidTimeFormat(workingToInput.value)) {
    showError("Invalid Working To time format. Please use hh:mm:ss");
    isValid = false;
  }

  if (!isValid) {
    event.preventDefault();
  }
}

function showError(message) {
  var errorMessage = document.getElementById("error-message");
  errorMessage.innerText = message;
}

function isValidTimeFormat(time) {
  // Regular expression for hh:mm:ss format
  var timeRegex = /^([01]\d|2[0-3]):([0-5]\d):([0-5]\d)$/;

  return timeRegex.test(time);
}
function displayErrorMessage(message) {
  var errorDiv = document.getElementById("error-message");
  errorDiv.innerText = message;
}
