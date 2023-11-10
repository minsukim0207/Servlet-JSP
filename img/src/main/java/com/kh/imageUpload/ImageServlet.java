package com.kh.imageUpload;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String jdbcUsername = "KHCAFE";
		String jdbcPassword = "KHCAFE";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			// sql
			String sql = "SELECT IMAGE FROM BOARD";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				// ResultSet�� image�� �ش��ϴ� �����͸� �����ͼ� blob�� ����
				// blob�� ����� ������ image �����͸� sql�� blob�� �����ϰڴٰ� �����߱� ������ Blob ��ü�� ������ �� ��
				Blob blob = rs.getBlob("image");
				byte[] imageData = blob.getBytes(1, (int)blob.length());	// Blob�� �����̱� ������ ������ ����Ʈ ������ �ɰ��� �о ��� �迭�� ����
				response.setContentType("image/jpeg");	// ���� ������ image/jped ���� ��Ÿ��
				response.getOutputStream().write(imageData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
