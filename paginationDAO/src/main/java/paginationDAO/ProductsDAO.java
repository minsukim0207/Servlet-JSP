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

	// 1. ��� ��ǰ�� ������ ���� �޼���
	// ������ ��ȣ�� ������ ũ�⸦ �޾ƿͼ� ����Ʈ�� �߰�
	public List<Products> getAllProducts(int pageNumber, int pageSize) {
		List<Products> productList = new ArrayList<>();
		// ���� �������� �� �������� ���� ���� ó��
		int start = PaginationUtil.paginationStart(pageNumber, pageSize);
		int end = PaginationUtil.paginationEnd(pageNumber, pageSize);
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT * FROM PRODUCTS"
					+ "ORDER BY PRODUCT_ID"	// product_id ������ ����
					+ "OFFSET ?"	// �������� ���� ��
					+ "ROWS FETCH NEXT ?"	// ������ ���� ��
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

	// 2. ��ü ��ǰ ���� ������ ���� �޼���
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
