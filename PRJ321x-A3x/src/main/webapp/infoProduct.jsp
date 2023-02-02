
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/product.css">
<title>Product Information</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="product container">

		<div class="row">
			
			<div class="preview col-md-6">
				<img src="${product.src}" class="img-responsive" />
			</div>
			<div class="details col-md-6">
				<h3 class="product-title">${product.name}</h3>
				<p class="product-description">${product.description}</p>
				<h4 class="price">
					current price: <span>${product.price}</span>
				</h4>
				<div class="action">
					<form class="form-inline" action="add-to-cart" method="post">
						<!-- Default input -->
						<input name="id" type="hidden" value="${product.id}">
						<input name="quantity" type="number" value="1"
							class="form-control" style="width: 100px">
						<button class="btn btn-warning btn-md" type="submit">
							ADD TO CART <span class="glyphicon glyphicon-shopping-cart"></span>
						</button>
					</form>
				</div>
			</div>
			
		</div>

	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>