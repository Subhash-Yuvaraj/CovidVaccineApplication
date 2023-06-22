// admin-dashboard.js

document.addEventListener("DOMContentLoaded", function() {
  // Add event listeners to the navigation links
  var addCentreLink = document.getElementById("addCentreLink");
  var viewCentreLink = document.getElementById("viewCentreLink");

  addCentreLink.addEventListener("click", handleAddCentre);
  viewCentreLink.addEventListener("click", handleViewCentre);
});

// Function to handle addCentre link click
function handleAddCentre(event) {
  event.preventDefault();
  // Implement the logic to handle addCentre link click
  console.log("Add Centre link clicked");
}

// Function to handle viewCentre link click
function handleViewCentre(event) {
  event.preventDefault();
  // Implement the logic to handle viewCentre link click
  console.log("View Centres link clicked");
}
