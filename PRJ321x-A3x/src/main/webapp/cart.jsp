<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping cart</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="container" style="margin-bottom: 3%">
		<%
		if (request.getAttribute("error") != null) {
		%>
		<div class="alert alert-danger">${error}.</div>
		<%
		}
		if (request.getAttribute("success") != null) {
		%>
		<div class="alert alert-success">${success}.</div>
		<%
		}
		%>
		<h2>Total Price: $ ${(cart.amount > 0)? cart.amount : 0}</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Name</th>
					<th>Type</th>
					<th>Brand</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Amount</th>
					<th>Cancel</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="o" items="${cart.items}">
					<tr>
						<td>${o.name}</td>
						<td>${o.type}</td>
						<td>${o.brand}</td>
						<td>${o.price}$</td>
						<td><a class="btn btn-sm"
							href="quantity-inc-dec?action=dec&id=${o.id}"><span
								class="glyphicon glyphicon-minus"></span> </a><input type="text"
							name="quantity" class="form-control"
							style="width: 50px; display: inline;" value="${o.number}"
							readonly> <a class="btn btn-sm"
							href="quantity-inc-dec?action=inc&id=${o.id}"><span
								class="glyphicon glyphicon-plus"></span> </a></td>
						<!-- chua lam tron -->
						<td>${Math.round(o.price*o.number * 100.0) / 100.0}$</td>
						<td><a class="btn btn-danger btn-sm"
							href="add-to-cart?action=delete&id=${o.id}">Remove</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="row">
			<div class="col-sm-6"></div>
			<div class="col-sm-6">
				<form action="pay" method="post">
					<table class="table table-hover">
						<tbody>
							<input type="hidden" name="mail" value="${user.usr}">
							<tr>
								<!-- dung ham java de cat bot ky tu ten hien thi-->
								<td>Customer name</td>
								<td><input type="text" name="username" value="${s}"
									style="text-align: center;" readonly></td>
							</tr>
							<tr>
								<td>Customer address</td>
								<td><input type="text" name="address"
									value="${user.address}" style="text-align: center;" readonly></td>
							</tr>
							<tr>
								<td>Discount code (if any)</td>
								<td><input type="text" name="discount" value="None"
									style="text-align: center;" readonly></td>
							</tr>
						</tbody>
					</table>
					<input type="submit" class="btn btn-warning" value="Submit">
				</form>
			</div>
		</div>

	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>