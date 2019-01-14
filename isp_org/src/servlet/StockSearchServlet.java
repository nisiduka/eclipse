package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StockDAO;
import dto.StockDTO;

/**
 * 銘柄検索の処理
 */
@WebServlet("/StockSearch")
public class StockSearchServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインしているか検証
		HttpSession session = request.getSession();
		if (session.getAttribute("accountId") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		// 入力値を取得
		request.setCharacterEncoding("UTF-8");
		String keyword = request.getParameter("keyword");
		// 数値以外か検証
		if(keyword.matches("^[^0-9]+$")) {
			// 銘柄名で銘柄一覧を取得
			List<StockDTO> instrumentList = StockDAO.getStockList(keyword);
			request.setAttribute("instrumentList", instrumentList);

			if(instrumentList.isEmpty()) {
				request.setAttribute("errorMessage", "存在する銘柄名を入力してください。");
				request.getRequestDispatcher("StockSearch.jsp").forward(request, response);

			}

			// 画面遷移
			request.getRequestDispatcher("StockSearch.jsp").forward(request, response);


			// 画面遷移
			request.getRequestDispatcher("StockSearch.jsp").forward(request, response);

		//4桁の銘柄コードを検証
		}else if(keyword.matches("^[0-9]{4}$")){
			// 銘柄コードで銘柄一覧を取得
			List<StockDTO> instrumentList = StockDAO.getStockListc(keyword);
			request.setAttribute("instrumentList", instrumentList);

			// 画面遷移
			request.getRequestDispatcher("StockSearch.jsp").forward(request, response);

		}else {
			request.setAttribute("errorMessage", "入力に誤りがあります。４桁の銘柄コードまたは銘柄名を入力してください。");
			request.getRequestDispatcher("StockSearch.jsp").forward(request, response);
		}




	}
}
