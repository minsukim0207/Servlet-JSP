<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Ȩ������</title>
<script>
	function displayLoginFail() {
		alert("�α��� ����");
	}
</script>
</head>
<body>
	<h1>Ȩ�������� ���Ű� ȯ���մϴ�.</h1>
	<!-- ���� �α��� ������ ��� �α��� ��ư�� ����� �α׾ƿ� ��ư�� ���̰� �ϰ� ���� ��� -->
	<%
		// ���� mno ���� ������ ���
		if(session.getAttribute("mno") != null) {
	%>
		<a href="logout.jsp">�α׾ƿ�</a>
	<%
		} else {
	%>
		<a href="login.jsp">�α���</a>
	<%
		}
	%>
	<%
		// �α��� ���� �� ����ڿ��� �α��� ���и� �˸��� ���� �α��� ���� �Լ� ȣ��
		// 1. �α��� ���� ���� �ľ�
		// 2. �Լ� ȣ��
		
		String loginError = (String)request.getAttribute("loginError");	// request.getAttribute("loginError")�� � ���� �����Ǿ� ���� �ʱ� ������ (String)���� ���� �����ؼ� ��ȯ
		if (loginError != null) {	// loginError != null ���� ������ ������ LoginServlet���� loginError���� true�� ������ ��� ������ null���� �� �� ����
	%>
	<script>
		displayLoginFail();
	</script>
	<%
		}
	%>
</body>
</html>