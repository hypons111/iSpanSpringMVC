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
	<form id="insert" method="post" action="insert" enctype="multipart/form-data">
		<p><label>產品種類</label><select id="type" class="input" name="type"></select></p>
		<p><label>產品名稱</label><input id="name" class="input" type="text" name="name"></p>
		<p><label>產品存量</label><input id="stock" class="input" type="text" name="stock"></p>
		<p><label>產品買價</label><input id="cost" class="input" type="text" name="cost"></p>
		<p><label>產品售價</label><input id="price" class="input" type="text" name="price"></p>
		<p><label>產品圖片</label><input id="image" class="input" type="file" name="file" /></p>
		<p>
			<button id="submit" type="submit">新增</button>
			<button type="reset">重設</button>
		</p>
		<p id="submitResult"></p>
	</form>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="js/productInsert.js"></script>
</body>
</html>
