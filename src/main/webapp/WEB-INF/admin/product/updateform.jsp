<%@ page import="springproject.product.dao.ProductDao"%>
<%@ page import="java.util.List"%>
<%@ page import="springproject.product.model.Product"%>
<%@ page import="com.google.gson.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="editForm" method="post" action="update" enctype="multipart/form-data">
		<table border="1">
			<thead>
				<tr>
					<th>Type</th>
					<th>ID</th>
					<th>Name</th>
					<th>Stock</th>
					<th>Buy</th>
					<th>Sell</th>
					<th>Image</th>
				</tr>
			</thead>
			<tbody id="resultTable"></tbody>
		</table>
	</form>
	<p id="submitResult"></p>
	</div>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>
	<script src="js/productUpdate.js"></script>
</body>
</html>

