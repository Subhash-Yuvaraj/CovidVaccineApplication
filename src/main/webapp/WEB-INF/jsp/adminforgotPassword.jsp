<html>
  <head>
    <title tiles:fragment="title">Forgot password</title>
    <link rel="stylesheet" href="/css/userforgotPassword.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
  </head>
  <body>
    <div tiles:fragment="content">
    <div class="wrapper">
    	<font color="red">${error}</font>
        <form class="form" action="/adminforgotPassword" method="post">   
        	<div>
        	<p>We will be sending a reset link to your mail</p>
        	</div>            
            <fieldset>
                <legend>Reset Password</legend>
                <div class="field email">
                	<div class="input-area">
                		<label for="email">Enter your e-mail</label>
                		<input type="text" id="email" name="email"/>   
                	</div>
                </div>         
                <div class="form-actions">
                    <button type="submit" style="border-radius:32px; backgorund-color:#2FC4F3; text-decoration:none; border:none; font-size:20px; min-height:34px; min-width:120px; margin-top:20px">Send</button>
                </div>
                
            </fieldset>
        </form>
        <p id="error">${error}</p>
        </div>
    </div>
  </body>
</html>