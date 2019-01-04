package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrdersellbuyDAO;
import dto.OrdersellbuyDTO;

/**
 * 注文処理
 */
@WebServlet("/BuyOrderComplete")
public class BuyOrderCompleteServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインしているか検証
		HttpSession session = request.getSession();
		String accountId = (String) session.getAttribute("accountId");
		if (accountId == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		// フォームの値を取得
		request.setCharacterEncoding("UTF-8");
		String code = request.getParameter("code");
		String quantity = request.getParameter("quantity");
		String orderType = request.getParameter("orderType");
		String price = request.getParameter("price");
		String condition = request.getParameter("condition");
		String buyfee = request.getParameter("buyfee");

		// 注文DTOを作成
		OrdersellbuyDTO order = new OrdersellbuyDTO();
		order.setAccountId(accountId);
		order.setStockCode(code);
		order.setQuantity(Integer.parseInt(quantity));
		order.setOrderPrice(Double.parseDouble(price));
		order.setOrderType(orderType);
		order.setOrderCondition(condition);
		order.setBuyfee(buyfee);

		String number = OrdersellbuyDAO.getNumber();
		System.out.print(number);
		
		
		// 注文を実行
		String orderId =
				OrdersellbuyDAO.createOrder(order,number);
		
		
		request.setAttribute("orderId", orderId);

		request.getRequestDispatcher("BuyOrderComplete.jsp").forward(request, response);
	}
}
