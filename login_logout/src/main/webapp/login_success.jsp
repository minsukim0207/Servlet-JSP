<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α��� ����</title>
<script>
	// 3�� �� Ȩ�������� �̵�
	setTimeout(function() {
		// �̵��� ������ ��� ����
		window.location.href="home.jsp";
	}, 3000);
</script>
</head>
<body>
<!-- �α��� ���� �� ���ǿ� ����� ����� �̸� �������� -->
<%
	String memail = (String)session.getAttribute("memail");
%>
	<p>ȯ���մϴ�. <%=memail%>��</p>
	<p>3�� �� Ȩ�������� �̵��մϴ�.</p>
	<p><a href="logout.jsp">�α׾ƿ�</a>
</body>
</html>