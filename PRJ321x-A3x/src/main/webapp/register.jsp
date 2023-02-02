
<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<style>
<%@ include file="css/register.css"%>
</style>
</head>
<body>
	<%@ include file="header.jsp"%>

    <div class="container-fluid bg">
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-12"></div>
            <div class="col-md-4 col-sm-4 col-xs-12">
                <!--form start-->
                <form class="form-container" action="register" method="post">
                  <h2>CREATE AN ACCOUNT</h2>
                  <% if(request.getAttribute("error") != null) {%>
                	  <div class="alert alert-danger">
                	  ${error}.
                	</div>
                  <% }
                  %>
                    <div class="form-group">
                      <label for="user_mail">Email address</label>
                      <input type="email" class="form-control" name="user_mail" placeholder="Your Email" required>
                    </div>
                    <div class="form-group">
                      <label for="user_name">Name</label>
                      <input type="text" class="form-control" name="user_name" placeholder="Your Name" required>
                    </div>
                    <div class="form-group">
                      <label for="password">Password</label>
                      <input type="password" class="form-control" name="password" placeholder="Your Password" required>
                    </div>
                    <div class="form-group">
                        <label for="re_pass">Repeat your password</label>
                        <input type="password" class="form-control" name="re_pass" placeholder="Repeat Your Password" required>
                    </div>
                    <div class="checkbox">
                      <label>
                        <input type="checkbox" name="agree" required> I agree all statements in <a href="#"><u>Terms of service</u></a>
                      </label>
                    </div>
                    <button type="submit" class="btn btn-success">Register</button>
                  </form>
                <!--form end-->
            </div>
            <div class="col-md-4 col-sm-4 col-xs-12"></div>
        </div>

    </div>  	

	<%@ include file="footer.jsp"%>
</body>
</html>