<html>
  <head>
    <title tiles:fragment="title">Reset password</title>
    <link rel="stylesheet" href="/css/resetPassword.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
  </head>
  <body>
    <div tiles:fragment="content">
    <div class="wrapper">
    	<font color="red">${error}</font>
        <form class="form" action="/adminresetpassword" method="post">   
        <h2>Reset your Password</h2>
        <div class="field email">
       		 <div class="input-area">
        		<input type="hidden" name="token" value="${token}"/>
        		<div>
        		</div>
        	</div>   
        </div>         
            <fieldset>
            	<div class = "field password">
            		<div class = "input-area">
                		<legend>New Password</legend>
                		
                		<input type="password" id="password" name="password" required/>  
                	</div> 
                </div>
                <div class = "field password">
                	<div class = "input-area">
                		<legend>Confirm Password</legend>
                		
                		<input type="password"  name="confirmpassword" required oninput="checkPasswordMatch(this)"/> 
                	</div>  
                </div>         
                <div class="form-actions">
                    <button type="submit" style="border-radius:32px; backgorund-color:#2FC4F3; text-decoration:none; border:none; font-size:20px; min-height:34px; min-width:120px; margin-top:20px">Change</button>
                </div>
            </fieldset>
        </form>
    </div>
    <script type="text/javascript">
    function checkPasswordMatch(fieldConfirmPassword) {
        if (fieldConfirmPassword.value != $("#password").val()) {
            fieldConfirmPassword.setCustomValidity("Passwords do not match!");
        } else {
            fieldConfirmPassword.setCustomValidity("");
        }
    }

    </script>
  </body>
</html>