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
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 与えられたIDとパスワードの組み合わせが存在するか確認
	 */
	public static boolean isRegistered(String id, String password) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/icwdb", "postgres", "password");
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
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/icwdb", "postgres", "password");
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
	 * 与えられたIDとパスワードの組み合わせが存在するか確認
	 */
	public static int getPwWrongCount(String id) {
		Connection con = null;
		PreparedStatement stmt = null;
		int count = 0;

		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/icwdb", "postgres", "password");
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
}
