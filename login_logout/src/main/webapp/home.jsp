<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>홈페이지</title>
<script>
	function displayLoginFail() {
		alert("로그인 실패");
	}
</script>
</head>
<body>
	<h1>홈페이지에 오신걸 환영합니다.</h1>
	<!-- 만약 로그인 상태일 경우 로그인 버튼을 숨기고 로그아웃 버튼을 보이게 하고 싶을 경우 -->
	<%
		// 만약 mno 값이 존재할 경우
		if(session.getAttribute("mno") != null) {
	%>
		<a href="logout.jsp">로그아웃</a>
	<%
		} else {
	%>
		<a href="login.jsp">로그인</a>
	<%
		}
	%>
	<%
		// 로그인 실패 시 사용자에게 로그인 실패를 알리기 위해 로그인 실패 함수 호출
		// 1. 로그인 실패 여부 파악
		// 2. 함수 호출
		
		String loginError = (String)request.getAttribute("loginError");	// request.getAttribute("loginError")는 어떤 값도 지정되어 있지 않기 때문에 (String)으로 값을 지정해서 반환
		if (loginError != null) {	// loginError != null 값을 설정한 이유는 LoginServlet에서 loginError값을 true로 설정해 줬기 때문에 null값이 될 수 없음
	%>
	<script>
		displayLoginFail();
	</script>
	<%
		}
	%>
</body>
</html>