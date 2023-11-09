<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.kh.product.Product" %>
<%@ page import="com.kh.product.ProductDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>제품 상세</title>
</head>
<body>
	<h1>제품 상세</h1>
	<%
		String productIdValue = request.getParameter("productId");
		int productId = Integer.parseInt(productIdValue);
		//DAO 작업
		ProductDAO productDAO = new ProductDAO();
		Product product = productDAO.getProductId(productId);
	%>
	<p>제품 ID : <%= product.getProductId() %></p>
	<p>제품 이름 : <%= product.getProductName() %></p>
	<p>카테고리 : <%= product.getCategory() %></p>
	<p>가격 : <%= product.getPrice() %></p>
	<p>수량 : <%= product.getStockQuantity() %></p>
</body>
</html>