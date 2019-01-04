package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StockDAO;
import dao.FavorDAO;
import dto.StockDTO;

/**
 * 買い注文入力画面の処理
 */
@WebServlet("/favorite")
public class favoriteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインしているか検証
		HttpSession session = request.getSession();
		if (session.getAttribute("accountId") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		//URLから銘柄コードを取得
		request.setCharacterEncoding("UTF-8");
		String code = request.getParameter("code");
		
		//セッションスコープからacctIdを取り出す
		String accountId = (String)session.getAttribute("accountId");
		System.out.print(accountId);
		
		//favorDTOに銘柄をお気に入りとして登録
		if(FavorDAO.Favornum(code, accountId)) {
			String stockcde = FavorDAO.createFavor(code,accountId);
			request.setAttribute("stockcde", stockcde);
			request.getRequestDispatcher("FavorComplete.jsp").forward(request,response);
		
		}else {
			
			// 銘柄DTOを取得
			StockDTO detail = StockDAO.getStock(code);
			request.setAttribute("detail", detail);
			request.setAttribute("errorMessage", "この株式銘柄はすでにお気に入り登録されています。");
			request.getRequestDispatcher("StockDetail").forward(request, response);
		}
		
	}
	
}
