

import java.io.IOException;
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
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String jdbcUsername = "KHCAFE";
		String jdbcPassword = "KHCAFE";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		
			// login
			String memail = request.getParameter("m_email");
			String mno = request.getParameter("m_no");
		
			String sql = "SELECT * FROM MEMBERINFO WHERE MEMAIL=? AND MNO=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memail);
			preparedStatement.setString(2, mno);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// �α��� �õ� ���� Ȯ��
			if (resultSet.next()) {
				// HttpSession : ������ Ŭ���̾�Ʈ�� ���� ���� ������ �����ϰ� �����ϴµ� ���
				HttpSession session = request.getSession();	// ���� http ��û�� ���� ������ ������ ��. ������ �̹� �����ϴ� ��� �ش� ������ ������ ��
				session.setAttribute("mno", resultSet.getInt("MNO"));
				session.setAttribute("mname", resultSet.getString("MName"));
				session.setAttribute("memail", memail);
				session.setAttribute("mbirth", resultSet.getDate("MBirth"));
				
				// �α��� ������ 30�� ����
				session.setMaxInactiveInterval(1800);
				
				// �α��ο� �����ϸ� ������ ���� �����͸� login_success�� ����
				response.sendRedirect("login_success.jsp");
			} else {
				// ���� �� �Ӽ��� loginError��� �̸����� �Ӽ��� �����ϰ�, �α��� ������ true�� ������ �߱� ������ �α��� ������ �߻������� ��Ÿ���� �Ӽ� �̸��� �Ӽ� ���� �߰�
				request.setAttribute("loginError", "true");
				request.getRequestDispatcher("home.jsp").forward(request, response);	// getRequestDispatcher(���) : ������ ��η� �̵���Ű�� ���� ��ü ��ȯ // forward(request, response) : ���� ������ ������ �ߴ� // ���ݱ��� ������ �ִ� �����͸� Ŭ���̾�Ʈ���� �������� ������ ����� ǥ��
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
