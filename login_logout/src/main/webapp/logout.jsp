<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	// ������ �����Ͽ� �α׾ƿ� ó��
	session.invalidate();
	response.sendRedirect("home.jsp");
%>