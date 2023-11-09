package com.kh.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUsername = "KHCAFE";
	private static final String jdbcPassword = "KHCAFE";
	Connection conn;
	
	public ProductDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT * FROM PRODUCTS";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int productId = rs.getInt("product_id");
				String productName = rs.getString("product_name");
				String category = rs.getString("category");
				double price = rs.getDouble("price");
				int stockQuantity = rs.getInt("stock_quantity");
				
				Product product = new Product(productId, productName, category, price, stockQuantity);
				products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	
	public Product getProductId(int productId) {
		Product product = null;
		// select로 한개만 가져오는 쿼리 작성
		try {
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productId);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			int productId1 = rs.getInt("product_id");
			String productName = rs.getString("product_name");
			String category = rs.getString("category");
			Double price = rs.getDouble("price");
			int stockQuantity = rs.getInt("stock_quantity");
			
			product = new Product(productId1, productName, category, price, stockQuantity);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
}
