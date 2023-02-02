

<!DOCTYPE html>
<html>
<head>
<title>Home page</title>
<style>
<%@ include file="css/productCard.css"%>
</style>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="container">
		<div class="row">
			<c:forEach var="o" items="${listSearch}">
				<div class="col-sm-4">
					<div class="card">
						<a href="information-product?&id=${o.id}"><img src="${o.src}"
							class="img-responsive" style="width: 100%" alt="Image" /></a>
						<h1 class="title">${o.type}</h1>
						<h3>${o.name}</h3>
						<p class="price">${o.price}</p>
						<a href="add-to-cart?action=add&quantity=1&id=${o.id}"
							class="btn btn-primary">Add to Cart</a>
					</div>
				</div>
			</c:forEach>

		</div>
		<br> <br>
		<ul class="pagination">
			<c:forEach begin="1" end="${endPage}" var="i">
				<li><a id="${i}"
					href="search3?index=${i}&txtSearch=${txtSearch}">${i}</a></li>
			</c:forEach>
		</ul>
	</div>
	<script>
		document.getElementById("${index}").style.color = "red";
	</script>

	<%@ include file="footer.jsp"%>
</body>
</html>