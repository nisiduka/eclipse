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
 * 買い注文入力画面の処理
 */
@WebServlet("/BuyOrder")
public class BuyOrderServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインしているか検証
		HttpSession session = request.getSession();
		if (session.getAttribute("accountId") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		// URLパラメーターから銘柄コードを取得
		request.setCharacterEncoding("UTF-8");
		String code = request.getParameter("code");

		// 銘柄DTOを取得
		StockDTO instrument = StockDAO.getStock(code);
		request.setAttribute("instrument", instrument);

		// エラーメッセージを取得
		String error = request.getParameter("error");
		if ("quantityError".equals(error)) {
			request.setAttribute("errorMessage", "注文数量に誤りがあります");
		} else if ("priceError".equals(error)) {
			request.setAttribute("errorMessage", "注文価格に誤りがあります");
		} else if ("upperlimiterror".equals(error)) {
			request.setAttribute("errorMessage", "注文可能額を超えています");
		}

		request.getRequestDispatcher("BuyOrder.jsp").forward(request, response);
	}
}
