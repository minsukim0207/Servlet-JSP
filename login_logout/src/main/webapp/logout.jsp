<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	// 세션을 삭제하여 로그아웃 처리
	session.invalidate();
	response.sendRedirect("home.jsp");
%>