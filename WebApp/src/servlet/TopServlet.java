package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * トップ画面
 */
@WebServlet("/Top")
public class TopServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインしているか検証
		HttpSession session = request.getSession();
		if (session.getAttribute("accountId") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		request.getRequestDispatcher("Top.jsp").forward(request, response);
	}
}
