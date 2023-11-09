package com.kh.product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {

	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUsername = "KHCAFE";
	private static final String jdbcPassword = "KHCAFE";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn;

		//데이터베이스 연결
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
			// SQL
			String sql = "SELECT * FROM PRODUCTS";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Product> productList = new ArrayList<>();

			while (rs.next()) {
				int productId = rs.getInt("productId");
				String productName = rs.getString("productName");
				String category = rs.getString("category");
				double price = rs.getDouble("price");
				int stockQuantity = rs.getInt("stockQuantity");
				
				Product product = new Product(productId, productName, category, price, stockQuantity);
				productList.add(product);	// 객체 배열에 추가
			}
			
			// JSP 페이지로 제품 목록 전달
			request.setAttribute("productList", productList);
			request.getRequestDispatcher("/productList.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
