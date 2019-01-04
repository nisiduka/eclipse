package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StockDAO;
import dto.StockDTO;

/**
 * 銘柄詳細の処理
 */
@WebServlet("/StockDetail")
public class StockDetailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインしているか検証
		HttpSession session = request.getSession();
		if (session.getAttribute("accountId") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		// URLパラメーターから銘柄コードを取得
		String code = request.getParameter("code");

		// 銘柄DTOを取得
		StockDTO detail = StockDAO.getStock(code);
		request.setAttribute("detail", detail);

		// 画面遷移
		request.getRequestDispatcher("StockDetail.jsp").forward(request, response);
	}
}
