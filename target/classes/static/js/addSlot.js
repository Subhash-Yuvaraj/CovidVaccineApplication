// AddSlot JavaScript

window.onload = function() {
  var dateInput = document.getElementById("date");
  var today = new Date();
  var tomorrow = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 1);
  var formattedTomorrow = formatDate(tomorrow);
  dateInput.setAttribute("min", formattedTomorrow);
}

function formatDate(date) {
  var year = date.getFullYear();
  var month = ("0" + (date.getMonth() + 1)).slice(-2);
  var day = ("0" + date.getDate()).slice(-2);
  return year + "-" + month + "-" + day;
}
