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
				// ResultSet의 image에 해당하는 데이터를 가져와서 blob에 저장
				// blob를 사용한 이유는 image 데이터를 sql의 blob에 저장하겠다고 지정했기 때문에 Blob 객체를 가지고 온 것
				Blob blob = rs.getBlob("image");
				byte[] imageData = blob.getBytes(1, (int)blob.length());	// Blob은 파일이기 때문에 파일을 바이트 단위로 쪼개서 읽어서 모두 배열에 저장
				response.setContentType("image/jpeg");	// 파일 형식이 image/jped 임을 나타냄
				response.getOutputStream().write(imageData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
