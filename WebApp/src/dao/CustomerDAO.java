package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 顧客情報を管理するDAO
 */
public class CustomerDAO {

	static {
		try {
			//JDBCドライバのロード
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 与えられたログインIDとパスワードの組み合わせが存在するか確認
	 */
	public static String getAcct(String id, String password) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			//データベース接続の確立
			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			//送信すべきSQL文の雛形を準備
			stmt = con.prepareStatement("SELECT ACCTID  FROM CUSTOMER WHERE LOGINID = ? AND PASSWORD = ?");
			//setStringで雛形へ値を流し込む
			stmt.setString(1, id);
			//setStringで雛形へ値を流し込む
			stmt.setString(2, password);
			//excuteUpdateメソッド
			ResultSet rs = stmt.executeQuery();
			//nextメソッド()で一行ずつ進める
			//get〜()メソッドで必要な列を取り出す
			rs.next();
			String acctId = rs.getString("acctid");

		return acctId;

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// それ以外の場合は、例外発生時も含めすべてfalse
	return null;
}






	public static boolean isRegisteredLog(String id, String password) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			stmt = con.prepareStatement("SELECT count(*) as count FROM CUSTOMER WHERE LOGINID = ? AND PASSWORD = ?");

			stmt.setString(1, id);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			rs.next();
			String count = rs.getString("count");

			if (count.equals("1")) {
				// 一致するレコードが1件だけ存在する場合のみtrue

				return true;
			}
		//eがインスタンスでprintStackTraceで記述している
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// それ以外の場合は、例外発生時も含めすべてfalse
		return false;
	}



	/**
	 * 与えられた口座番号とパスワードの組み合わせが存在するか確認
	 */
	public static boolean isRegistered(String id, String password) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			stmt = con.prepareStatement("SELECT count(*) as count FROM CUSTOMER WHERE ACCTID = ? AND PASSWORD = ?");

			stmt.setString(1, id);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			rs.next();
			String count = rs.getString("count");

			if (count.equals("1")) {
				// 一致するレコードが1件だけ存在する場合のみtrue
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// それ以外の場合は、例外発生時も含めすべてfalse
		return false;
	}

	//パスワード間違った回数集計
	public static void pwWrongCount(String id,String type) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			if(type.equals("カウントアップ")){
				stmt = con.prepareStatement("UPDATE CUSTOMER SET PWCOUNT = PWCOUNT+1 WHERE ACCTID = ?");
			}else if(type.equals("リセット")){
				stmt = con.prepareStatement("UPDATE CUSTOMER SET PWCOUNT = 0 WHERE ACCTID = ?");
			}

			stmt.setString(1, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 与えられたIDとパスワードの回数確認
	 */
	public static int getPwWrongCount(String id) {
		Connection con = null;
		PreparedStatement stmt = null;
		int count = 0;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			stmt = con.prepareStatement("SELECT PWCOUNT as count FROM CUSTOMER WHERE ACCTID = ?");

			stmt.setString(1, id);

			ResultSet rs = stmt.executeQuery();

			rs.next();
			count = rs.getInt("count");

			return count;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return count;
	}

	/**
	 * ログインIDとパスワードを作成する loginIDとパスワードのみをupdateとする7_18
	 */

	public static boolean updateAccount(String acctid, String id,String password) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {

			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			stmt=con.prepareStatement("UPDATE CUSTOMER SET "
					+ "loginid = ? , password = ? "
					+ "where acctid = ?");

			stmt.setString(1, id);
			stmt.setString(2, password);
			stmt.setString(3, acctid);
			stmt.executeUpdate();

			return true;


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

}
