<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입 성공</h1>
	<h2>회원 가입 정보 :</h2>
	<p> 회원 번호 : <%= session.getAttribute("mno") %></P>
	<p> 이름 : <%= session.getAttribute("mname") %></p>
	<p> 이메일 : <%= session.getAttribute("memail") %></p>
	<p> 생년월일 : <%= session.getAttribute("mbirth") %></p>
</body>
</html>