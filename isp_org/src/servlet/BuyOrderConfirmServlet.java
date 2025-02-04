package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrdersellbuyDAO;
import dao.StockDAO;
import dto.OrdersellbuyDTO;
import dto.StockDTO;

/**
 * 注文内容確認画面
 */
@WebServlet("/BuyOrderConfirm")
public class BuyOrderConfirmServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインしているか検証
		HttpSession session = request.getSession();
		String accountId = (String) session.getAttribute("accountId");
		if (session.getAttribute("accountId") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		// 入力値を取得
		request.setCharacterEncoding("UTF-8");
		String quantity = request.getParameter("quantity");
		String orderType = request.getParameter("orderType");
		String price = request.getParameter("price");
		String condition = request.getParameter("condition");

		// 銘柄情報を取得
		String code = request.getParameter("code");
		StockDTO instrument = StockDAO.getStock(code);
		int unit = instrument.getUnit();



		// 注文数量のチェック
		if (!isValidQuantity(quantity, unit)) {
			response.sendRedirect("BuyOrder?code=" + code + "&error=quantityError");
			return;
		} else if (!isValidPrice(price)) {
			response.sendRedirect("BuyOrder?code=" + code + "&error=priceError");
			return;
		}else if (isValidUperlimit(accountId,price,quantity)) {
			response.sendRedirect("BuyOrder?code=" + code + "&error=upperlimiterror");
			return;
		}

		// 出力値をセット
		request.setAttribute("quantity", quantity);
		request.setAttribute("orderType", orderType);
		request.setAttribute("price", price);
		request.setAttribute("condition", condition);
		request.setAttribute("instrument", instrument);

		request.getRequestDispatcher("BuyOrderConfirm.jsp").forward(request, response);
	}

	// 注文数量のチェック
	private boolean isValidQuantity(String quantity, int unit) {
		int n;

		try {
			n = Integer.parseInt(quantity);
		} catch (NumberFormatException e) {
			// nullあるいは数字でない場合false
			return false;
		}

		if (n < 1 || 10_000_000 < n) {
			// 範囲内でない場合false
			return false;
		} else if (n % unit != 0) {
			// 単位株数の整数倍でない場合false
			return false;
		} else {
			return true;
		}
	}

	// 注文価格のチェック
	private boolean isValidPrice(String price) {
		int n;

		try {
			n = Integer.parseInt(price);
		} catch (NumberFormatException e) {
			// nullあるいは数字でない場合false
			return false;
		}

		if (n < 1 || 10_000_000 < n) {
			// 範囲内でない場合false
			return false;
		} else {
			return true;
		}
	}

	// 取引額上限のチェック
	private boolean isValidUperlimit(String accountId,String price,String quantity) {
		double p,q;
		double total = 0,sum;

		// 売買情報を取得
		List<OrdersellbuyDTO> orders = OrdersellbuyDAO.getOrder(accountId);

		for (OrdersellbuyDTO calc : orders) {
			p = calc.getOrderPrice();
			q = calc.getQuantity();
			total = p*q;
			total += total;
		}

		sum = total + (Double.parseDouble(price)*Double.parseDouble(quantity));

		if ( sum< 3000000) {
			// 範囲内でない場合false
			return false;
		} else {
			return true;
		}
	}
}
