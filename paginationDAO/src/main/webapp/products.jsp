<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="paginationDAO.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>제품 목록</title>
</head>
<body>
	<%
		int pageNumber = Integer.parseInt(request.getParameter("page"));
		int pageSize = 2;
		
		ProductsDAO pDAO = new ProductsDAO();
		List<Products> pl = pDAO.getAllProducts(pageNumber, pageSize);
	%>
	<table border = "1">
		<tr>
			<th>제품명</th>
			<th>카테고리</th>
		</tr>
	<%
		for (Products p : pl) {
	%>
	<tr>
		<td><%= p.getProductId() %></td>
		<td><%= p.getProductName() %></td>
	</tr>
	</table>	
	<%
		}
	
		// 1. 페이지네이션 링크 생성. 링크는 page 값에 따라서 다르게 보임
		int totalProducts = pDAO.getTotalProducts();
		int totalPages = (int) Math.ceil((double)totalProducts / pageSize);
		
		for (int i = 1; i <= totalPages; i++) {
	%>
		<a href="<%= request.getRequestURI() %>?page=<%= i %>"><%= i %></a>
	<%
		}
	%>
</body>
</html>