

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
			
			// 로그인 시도 유저 확인
			if (resultSet.next()) {
				// HttpSession : 웹에서 클라이언트와 서버 간에 정보를 유지하고 공유하는데 사용
				HttpSession session = request.getSession();	// 현재 http 요청에 대한 세션을 가지고 옴. 세션이 이미 존재하는 경우 해당 세션을 가지고 옴
				session.setAttribute("mno", resultSet.getInt("MNO"));
				session.setAttribute("mname", resultSet.getString("MName"));
				session.setAttribute("memail", memail);
				session.setAttribute("mbirth", resultSet.getDate("MBirth"));
				
				// 로그인 세션을 30분 유지
				session.setMaxInactiveInterval(1800);
				
				// 로그인에 성공하면 성공에 대한 데이터를 login_success에 전달
				response.sendRedirect("login_success.jsp");
			} else {
				// 실패 시 속성에 loginError라는 이름으로 속성을 저장하고, 로그인 에러가 true로 설정을 했기 때문에 로그인 오류가 발생했음을 나타내는 속성 이름과 속성 값을 추가
				request.setAttribute("loginError", "true");
				request.getRequestDispatcher("home.jsp").forward(request, response);	// getRequestDispatcher(경로) : 설정한 경로로 이동시키기 위한 객체 반환 // forward(request, response) : 현재 페이지 실행이 중단 // 지금까지 가지고 있는 데이터를 클라이언트에게 응답으로 보내고 결과를 표시
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
