// Add your custom JavaScript code here

$(document).ready(function() {
  // Toggle switch event handler
  $('.toggle-switch input[type="checkbox"]').change(function() {
    var isChecked = $(this).prop('checked');
    
    if (isChecked) {
      $(this).closest('.toggle-switch').addClass('active');
    } else {
      $(this).closest('.toggle-switch').removeClass('active');
    }
  });
});
