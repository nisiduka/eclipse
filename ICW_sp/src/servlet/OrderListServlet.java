package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import dto.*;

/**
 * 注文一覧の処理
 */
@WebServlet("/OrderList")
public class OrderListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインしているか検証
		HttpSession session = request.getSession();
		String accountId = (String) session.getAttribute("accountId");
		if (accountId == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		// 注文一覧を取得する
		List<OrdersellbuyDTO> orders = OrdersellbuyDAO.getOrdersellbuyList(accountId);
		request.setAttribute("orders", orders);

		// 画面遷移
		request.getRequestDispatcher("OrderList.jsp").forward(request, response);
	}
}
