// Function to handle form submission and perform AJAX request
function searchSlots() {
    // Prevent form submission
    event.preventDefault();

    // Get the form input values
    var pin = document.getElementById("pinInput").value;
    var workingFrom = document.getElementById("workingFromInput").value;

    // Create a new XMLHttpRequest object
    var xhr = new XMLHttpRequest();

    // Configure the AJAX request
    xhr.open("GET", "/bookSlots?pin=" + pin + "&workingFrom=" + workingFrom, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // Define the callback function for AJAX request
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Request was successful
                var response = xhr.responseText;

                // Update the slots table with the received data
                var slotsTable = document.getElementById("slotsTable");
                slotsTable.innerHTML = response;
            } else {
                // Request failed
                console.log("Error: " + xhr.status);
            }
        }
    };

    // Send the AJAX request
    xhr.send();
}

// Attach the searchSlots function to the form submit event
document.getElementById("searchForm").addEventListener("submit", searchSlots);
