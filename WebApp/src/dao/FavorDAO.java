package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.FavorDTO;
import dto.OrdersellbuyDTO;


public class FavorDAO {

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * お気に入り済み判定
	 */

	public static boolean Favornum(String stockcde,String acctId) {
		//パラメーター初期化
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"select count(*) as count from favor "
					+ "where acctId = '"+ acctId + "' and  stockcde = '" + stockcde + "'");

			rs.next();
			String count = rs.getString("count");
			if(count.equals("0")) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return false;
	}
}


	/**
	 *
	 * お気に入り銘柄書き込み
	 */
	//お気に入り銘柄の書き込み

	public static String createFavor(String stockcde,String acctId) {
		Connection con = null;
		PreparedStatement stmt = null;
		String favorite = "";

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			stmt = con.prepareStatement(
					"INSERT INTO FAVOR (FAVORITE,ACCTID,STOCKCDE) VALUES (?,?,?) ");

			favorite =  new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
			stmt.setString(1,favorite);
			stmt.setString(2,acctId);
			System.out.println(acctId);
			stmt.setString(3,stockcde);

			// クエリの実行
			stmt.executeUpdate();
			System.out.println(acctId);
			return stockcde;

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

		// 受付番号を返す
			return stockcde;
	}

	/**
	 * お気に入り
	 * 一覧
	 */
	public static List<FavorDTO> getFavorList(String accountId) {

		ArrayList<FavorDTO> list = new ArrayList<FavorDTO>();
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			stmt = con.createStatement();
//
//			ResultSet rs = stmt.executeQuery(
//					"select T1.*,T2.MEIGARA_NAME_DBCS,T3.PRICE,T4.MARKETNAME from ORDERS T1,STOCK T2,STOCK_STATUS T3,MARKET T4 where T1.STOCKCDE = T2.STOCKCDE AND T1.STOCKCDE = T3.STOCKCDE AND T2.MARKETCDE = T4.MARKETCDE AND T1.ACCTID = '"
//							+ accountId + "' ORDER BY ORDERNO DESC");
//			ResultSet rs = stmt.executeQuery(
//					"select T1.favorite T2.* from favor T1,stock T2 where T1.stockcde=T2.stockcde and acctid= '" + accountId + "'");
			ResultSet rs = stmt.executeQuery(
					"select * from favor,stock where favor.stockcde = stock.stockcde and acctid = '" + accountId + "'");

			while (rs.next()) {
				FavorDTO favor = new FavorDTO();
				favor.setFavorite(rs.getString("favorite"));
				favor.setStockCode(rs.getString("STOCKCDE"));
				favor.setAccountId(rs.getString("acctid"));
				favor.setStockName(rs.getString("MEIGARA_NAME_DBCS"));

				list.add(favor);
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
		return list;
	}

	/**
	 * お気に入り登録
	 *
	 */

	public static boolean removeFavor(String favorite) {
		Connection con = null;
		PreparedStatement stmt = null;
	//	String orderId = "";

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			stmt = con.prepareStatement(
					"DELETE FROM favor WHERE favorite = ?");

			stmt.setString(1,favorite);

			// クエリの実行
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

		// 受付番号を返す
		return false;
	}


	/**
	 * 注文詳細
	 */
	public static OrdersellbuyDTO getFavoriteStock(String favorite) {

		Connection con = null;
		Statement stmt = null;
		OrdersellbuyDTO sellbuy = new OrdersellbuyDTO();

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"select T1.* from stock T1,favor T2 where T1.stockcde = T2.stockcde and favorite = '" + favorite + "'");

			rs.next();
			System.out.print(rs.getString("stockcde"));

			sellbuy.setStockCode(rs.getString("STOCKCDE"));
			sellbuy.setStockName(rs.getString("MEIGARA_NAME_DBCS"));
			sellbuy.setOrderType(rs.getString("ORDER_TYPE"));
			sellbuy.setQuantity(rs.getInt("NUM_ORDER"));
			sellbuy.setOrderPrice(rs.getInt("ORDERPRICE"));
			return sellbuy;

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

		return sellbuy;
	}


}




