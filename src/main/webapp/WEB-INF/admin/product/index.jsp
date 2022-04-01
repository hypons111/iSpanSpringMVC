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
<title>PRODUCT INDEX PAGE</title>
</head>
<body>
	<img alt="no image"
		src="http://localhost:8080/iSpanSpringMVC/images/orz.png" width="70"
		height="70" />
	<div style="margin-top: 20px">
		<table>
			<tbody>
				<tr>
					<th><a href="insertform.jsp"><button>Add New
								Product</button></a></th>
					<th><button id="showAll">Show All Product</button></th>
				</tr>
			</tbody>
		</table>
		<table border="2" class="table table-bordered">
			<thead>
				<tr>
					<th><p id="totalNum"></p></th>
					<th><button class="product_Type" id="sort">Type</button> <input
						id="type" class="columnSearchInput" type="text" placeholder=""
						value=""></th>
					<th><button class="product_ID" id="sort">ID</button> <input
						id="id" class="columnSearchInput" type="text" placeholder=""
						value=""></th>
					<th><button class="product_Name" id="sort">Name</button> <input
						id="name" class="columnSearchInput" type="text" placeholder=""
						value=""></th>
					<th><button class="product_Stock" id="sort">Stock</button> <input
						id="stock" class="columnSearchInput" type="text" placeholder=""
						value=""></th>
					<th><button class="product_Cost" id="sort">Buy</button> <input
						id="cost" class="columnSearchInput" type="text" placeholder=""
						value=""></th>
					<th><button class="product_Price" id="sort">Sell</button> <input
						id="price" class="columnSearchInput" type="text" placeholder=""
						value=""></th>
					<th>Image</th>
				</tr>
			</thead>
			<tbody id="resultTable"></tbody>
		</table>
	</div>

	 <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="../../js/product.js"></script>

</body>
</html>