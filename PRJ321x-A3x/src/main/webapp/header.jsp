<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"/>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
<%@ include file="css/header.css"%>
</style>
</head>
<body>
<header>
      <div class="container text-center">
      	<br><br>
      	<img alt="" src="https://seeklogo.com/images/M/mobile-shop-logo-12AAB672F0-seeklogo.com.jpg?v=637890946160000000">
      	<br><br><br><br>
      </div>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Mobile Shop</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="list">Home</a></li>
				<li><a href="#">Products</a></li>
				<li><a href="#">About Us</a></li>
			</ul>
			<form class="navbar-form navbar-left" action="search-2" method="post">
				<div class="input-group">
					<!-- co nen value = "${txtS}" -> de luu giu gia tri vua search xong -->
					<input type="text" class="form-control" placeholder="Search"
						name="search" value="${txtSearch}">
					<div class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${sessionScope.user != null}">
						<!-- kiem tra user la admin hay customer truoc khi hien ten -->
						<c:if test="${sessionScope.user.role == 1}">
							<li><a href="admin/index.jsp">Hello Admin ${s}</a></li>
						</c:if>
						<c:if test="${sessionScope.user.role == 0}">
							<li><a href="user-profile">Hello Customer ${s}</a></li>
							<li><a href="display-order"> Order </a></li>
						</c:if>
						
						<li><a href="logout"><span
								class="glyphicon glyphicon-log-out"></span> Logout</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="register.jsp"><span
								class="glyphicon glyphicon-user"></span> Sign Up</a></li>
						<li><a href="login"><span
								class="glyphicon glyphicon-log-in"></span> Login</a></li>
					</c:otherwise>
				</c:choose>
				<li><a href="cart.jsp"><span
						class="glyphicon glyphicon-shopping-cart"></span> Cart<span
						class="badge" style="background-color: red">${cart.items.size()}</span></a></li>
			</ul>
		</div>
	</nav>
</header>
</body>
</html>