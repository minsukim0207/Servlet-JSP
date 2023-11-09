<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kh.product.Product" %>
<%@ page import="com.kh.product.ProductDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Product List</title>
</head>
<body>
	<h1>제품 목록</h1>
	<table border="1">
		<tr>
			<th>제품 ID</th>
			<th>제품 이름</th>
			<th>카테고리</th>
			<th>가격</th>
			<th>수량</th>
		</tr>	
		<%
			ProductDAO productDAO = new ProductDAO();
			List<Product> products = productDAO.getAllProducts();
			
			for (Product product : products) {
		%>
		<tr>
			<td><a href="productDetail.jsp?productId=<%= product.getProductId() %>"><%= product.getProductId() %></a></td>
			<td><%= product.getProductName() %></td>
			<td><%= product.getCategory() %></td>
			<td><%= product.getPrice() %></td>
			<td><%= product.getStockQuantity() %></td>
		</tr>
		<%
			}
		%>
	</table>	
</body>
</html>

<!-- 
<ul>
		< %
		/*
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product(1, "노트북", "전자제품", 899.99, 3));
		products.add(new Product(2, "냉장고", "가전제품", 1089.99, 5));
		*/
		
		ArrayList<Product> products = (ArrayList<Product>)request.getAttribute("products");
		for (Product product : products) {
		%>
			<li>< %= product.getProductName() %>의 가격 < %= product.getPrice() % ></li>
		< %
		}
		% >
	</ul>
 -->