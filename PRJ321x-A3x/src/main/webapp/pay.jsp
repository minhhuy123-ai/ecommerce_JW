
<!DOCTYPE html>
<html>
<head>
<title>Pay</title>
<link rel="stylesheet" href="css/cart.css">
</head>
<body>
<!-- hien thi du lieu tu Order table va Order_detail table -->
	<%@ include file="header.jsp"%>

	<div class="container" style="margin-bottom:3%">
	<div class="panel panel-success">
      <div class="panel-heading">All Orders</div>
    </div>          
            <table class="table table-hover">
              <thead>
                <tr>
                  <th>Order Id</th>
                  <th>Product Id</th>
                  <th>Name</th>
                  <th>Amount</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="o" items="${listOrders}">
              	<tr>
                  <td>${o.orderId}</td>
                  <td>${o.productId}</td>
                  <td>${o.nameProduct}</td>
                  <td>${o.amountProduct}</td>
                </tr>
              </c:forEach>       
              </tbody>
            </table>
    
        </div>

	<%@ include file="footer.jsp"%>



</body>
</html>