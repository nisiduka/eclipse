package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDAO;

/**
 * ログイン処理
 */
@WebServlet("/Login1")
//送信元のactionとリクエスト先のサーブレットクラス、JSPで一致させる必要がある
public class LoginServlet1 extends HttpServlet {
	private static final int TIMEOUT_SEC = 600;

	@Override
	//リクエストパラメーターはHttpServletRequestのインスタンスに保存される
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字コードの設定
		request.setCharacterEncoding(UTF-8);
		
		// 入力されたログインIDとパスワードを取得
		//request.getParameter("リクエストパラメーターの名前")
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		// 口座番号の入力チェック
//		if (!id.matches("^[A-Z][0-9]{7}$")) {
//			request.setAttribute("errorMessage", "口座番号のフォーマットは正しくない");
//			request.getRequestDispatcher("Login.jsp").forward(request, response);
//		}

		// パスワードの入力チェック
		if (!password.matches("^\\p{ASCII}{6,16}$")) {
			request.setAttribute("errorMessage", "パスワードは6文字以上16文字以内の半角英数字または記号で入力してください");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}

//		//PW間違い回数チェック、4回以上だったらエラー
//		if(CustomerDAO.getPwWrongCount(id)>=4){
//			request.setAttribute("errorMessage", "パスワードの間違い上限が超えています。長谷川証券にお問合せください「0120-123-4567」");
//			request.getRequestDispatcher("Login.jsp").forward(request, response);
// }
		else if (CustomerDAO.isRegisteredLog(id, password)) {
			//間違い回数をリセット
			CustomerDAO.pwWrongCount(id, "リセット");


			// IDとパスワードの組み合わせが存在する場合、セッションを作成してトップ画面に遷移
			HttpSession session = request.getSession();

			//(accountId)の情報を入手する　DAOの中にacctIdを呼び出すメソッドを追加する
			String accuntId = CustomerDAO.getAcct(id, password);

			session.setAttribute("accountId", accuntId);
			session.setMaxInactiveInterval(TIMEOUT_SEC);

			response.sendRedirect("Top");
		} else {
			//間違い回数をカウントアップ
			CustomerDAO.pwWrongCount(id, "カウントアップ");

			// IDとパスワードの組み合わせが存在しない場合、画面を再表示
			request.setAttribute("errorMessage", "IDとパスワードの組み合わせが間違っています!!");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}
}




