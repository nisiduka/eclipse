package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDAO;

/**
 * ログアウト処理
 */
@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションを破棄
		HttpSession session = request.getSession(false);
		session.invalidate();

		// ログアウト画面に遷移
		request.getRequestDispatcher("Logout.jsp").forward(request, response);
	}

}
