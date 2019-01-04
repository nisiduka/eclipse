package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import dto.*;

/**
 * 注文詳細の処理
 */
@WebServlet("/OrderDetail")
public class OrderDetailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインしているか検証
		HttpSession session = request.getSession();
		if (session.getAttribute("accountId") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		// URLパラメーターから注文IDを取得
		String id = request.getParameter("id");

		// 注文DTOを取得
		OrdersellbuyDTO detail = OrdersellbuyDAO.getOrdersellbuy(id);
		request.setAttribute("detail", detail);

		// 画面遷移
		request.getRequestDispatcher("OrderDetail.jsp").forward(request, response);
	}
}
