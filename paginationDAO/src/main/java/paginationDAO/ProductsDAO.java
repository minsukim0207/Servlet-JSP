package paginationDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO {
	
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUsername = "KHCAFE";
	private static final String jdbcPassword = "KHCAFE";

	// 1. 모든 제품을 가지고 오는 메서드
	// 페이지 번호와 페이지 크기를 받아와서 리스트에 추가
	public List<Products> getAllProducts(int pageNumber, int pageSize) {
		List<Products> productList = new ArrayList<>();
		// 시작 페이지와 끝 페이지를 위한 변수 처리
		int start = PaginationUtil.paginationStart(pageNumber, pageSize);
		int end = PaginationUtil.paginationEnd(pageNumber, pageSize);
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT * FROM PRODUCTS"
					+ "ORDER BY PRODUCT_ID"	// product_id 기준을 정렬
					+ "OFFSET ?"	// 가져오기 시작 행
					+ "ROWS FETCH NEXT ?"	// 가져올 행의 수
					+ "ROWS ONLY";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Products products = new Products();
				products.setProductId(rs.getInt("PRODUCT_ID"));
				products.setProductName(rs.getString("PRODUCT_NAME"));
				productList.add(products);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	// 2. 전체 제품 수를 가지고 오는 메서드
	public int getTotalProducts() {
		int totalProducts = 0;
		try {
			Class.forName("oracle.jsbc.OracleDriver");
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
			String sql = "SELECT COUNT(*) AS TOTAL FROM PRODUCTS";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				totalProducts = rs.getInt("TOTAL");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return totalProducts;
	}
}
