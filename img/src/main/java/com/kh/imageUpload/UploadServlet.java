package com.kh.imageUpload;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/UploadServlet")
@MultipartConfig
/*
 * @MultipartConfig(	// ������ �ø��� ���� ���� ���� �����ϴ� ����
				fileSizeThreshold = 1024* 1024,	// ���� �����͸� ��ũ�� ����� �����ϱ� ���� �޸𸮿� �����Ǵ� �ִ�ũ��(1MB)
				maxFileSize = 1024 * 1024 * 5,	// ���ε��� ������ �ִ� ũ��(5MB)
				maxRequestSize = 1024 * 1024 * 10,	// ��û �������� �ִ� ũ��(10MB)
				location="/tmp"	// ������ ��ũ�� ����� �ӽ� ���͸�
) 
 */
public class UploadServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String jdbcUsername = "KHCAFE";
		String jdbcPassword = "KHCAFE";
		
		/*
		 * ����ڰ� ��û�� �� �����͸� ó���ϴµ� ����ϴ� ����
		 * jsp title �Ķ���͸� �����ͼ� title ���ڿ� ������ ����
		 * ������ �Էµ� ������ ��Ÿ��
		 */
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Part imagePart = request.getPart("image");
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "INSERT INTO BOARD (BOARD_ID, TITLE, CONTENT, IMAGE, CREATED_AT, AUTHOR)"
					+ "VALUES (board_sequence.nextval, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setBinaryStream(3, imagePart.getInputStream(), (int)imagePart.getSize());
			ps.setTimestamp(4, new Timestamp(new Date().getTime()));
			ps.setString(5, "�ۼ��� �̸�");
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("imageList.jsp");
	}

}
