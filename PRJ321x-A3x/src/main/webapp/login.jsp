
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<style>
<%@ include file="css/login.css"%>
</style>
</head>
<body>
	
	<%@ include file="header.jsp"%>
	
	<div class="container-fluid bg">
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-12"></div>
            <div class="col-md-4 col-sm-4 col-xs-12">
                <!--form start-->
                <form class="form-container" action="login" method="post">
                  <h1>Sign in</h1>
                  <% if(request.getAttribute("error") != null) {%>
                	  <div class="alert alert-danger">
                	  ${error}.
                	  </div>
                  <% }
                      if(request.getAttribute("success") != null){%>
                      <div class="alert alert-success">
                	  ${success}.
                	  </div>
                  <% } %>
                    <div class="form-group">
                      <label for="username">Email address</label>
                      <input type="email" class="form-control" name="username" value="${usermail}" placeholder="Email">
                    </div>
                    <div class="form-group">
                      <label for="password">Password</label>
                      <input type="password" class="form-control" name="password" value="${password}" placeholder="Password">
                    </div>
                    <div class="checkbox">
                      <label>
                        <input type="checkbox" name="remember"> Remember me
                      </label>
                    </div>
                    <button type="submit" class="btn btn-success">Submit</button>
                  </form>
                <!--form end-->
            </div>
            <div class="col-md-4 col-sm-4 col-xs-12"></div>
        </div>
    </div>
    
    <%@ include file="footer.jsp"%>
    
</body>
</html>