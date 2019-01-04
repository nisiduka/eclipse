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
@WebServlet("/FavorList")
public class FavorListServlet extends HttpServlet {
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
		//<FavorDTO>のリスト
		List<FavorDTO> favor = FavorDAO.getFavorList(accountId);
		request.setAttribute("favor", favor);

		// 画面遷移
		request.getRequestDispatcher("FavorList.jsp").forward(request, response);
	}
}
