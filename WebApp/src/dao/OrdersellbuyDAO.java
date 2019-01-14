package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.OrdersellbuyDTO;

/**
 * SampleDTOのListを返す
 */
public class OrdersellbuyDAO {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	//受付番号増加

	public static String getNumber() {

		Connection con = null;
		Statement stmt = null;
		String number = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			stmt = con.createStatement();

			//注文テーブルの最新の注文numberを取る
			ResultSet rs = stmt.executeQuery("select orderno from orders order by order_dt desc");
			/*mysqlではoffsetとlimitでは使えない　*/
			//ResultSet //rs = stmt.executeQuery("select orderno from orders order by order_dt desc offset 0 limit 1");

			rs.next();

			number = (String) rs.getString("orderno");

			//連番部分の取り出し
			number = number.substring(8,14);

			return number;

			}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			}
		}
	}

	return number;
}



//	public static void increaseNumber(String number1) {
//
//		Connection con = null;
//		PreparedStatement stmt = null;
//
//		try {
//
//			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/icwdb", "postgres", "password");
//			stmt = con.prepareStatement(" update stock set number = ? ");
//
//			int num = Integer.parseInt(number1);
//			num = num + 1;
//			String number = String.valueOf(num);
//
//			stmt.setString(1,number);
//
//			// クエリの実行
//			stmt.executeUpdate();
//
//			}catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//		if (stmt != null) {
//			try {
//				stmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}





	/**
	 * 注文一覧
	 */
	public static List<OrdersellbuyDTO> getOrdersellbuyList(String accountId) {

		ArrayList<OrdersellbuyDTO> list = new ArrayList<OrdersellbuyDTO>();
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"select T1.*,T2.MEIGARA_NAME_DBCS,T3.PRICE,T4.MARKETNAME from ORDERS T1,STOCK T2,STOCK_STATUS T3,MARKET T4 where T1.STOCKCDE = T2.STOCKCDE AND T1.STOCKCDE = T3.STOCKCDE AND T2.MARKETCDE = T4.MARKETCDE AND T1.ACCTID = '"
							+ accountId + "' ORDER BY ORDERNO DESC");

			while (rs.next()) {
				OrdersellbuyDTO sellbuy = new OrdersellbuyDTO();
				sellbuy.setOrderID(rs.getString("ORDERNO"));
				// sellbuy.setAccountID (rs.getString("account_id"));
				sellbuy.setStockCode(rs.getString("STOCKCDE"));
				sellbuy.setStockName(rs.getString("MEIGARA_NAME_DBCS"));
				sellbuy.setOrderType(rs.getString("ORDER_TYPE"));
				sellbuy.setQuantity(rs.getInt("NUM_ORDER"));
				sellbuy.setOrderPrice(rs.getInt("ORDERPRICE"));

				sellbuy.setOrderDate(rs.getString("ORDER_DT"));
				sellbuy.setOrderStatus(rs.getString("STATUS"));
				sellbuy.setStockPrice(rs.getString("PRICE"));
				// sellbuy.setTransactiondate(rs.getString("transaction_date"));
				sellbuy.setMarket(rs.getString("MARKETNAME"));
				// sellbuy.setMarket(rs.getString("order_id"));
				// sellbuy.setOrderTimeout (rs.getString("order_"));
				sellbuy.setOrderCondition(rs.getString("CONDITIONS"));
				sellbuy.setSellBuy(rs.getString("SELL_BUY"));

				list.add(sellbuy);
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
	 * 注文詳細
	 */
	public static OrdersellbuyDTO getOrdersellbuy(String orderId) {

		Connection con = null;
		Statement stmt = null;
		OrdersellbuyDTO sellbuy = new OrdersellbuyDTO();

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"select T1.*,T2.MEIGARA_NAME_DBCS,T3.PRICE,T4.MARKETNAME from ORDERS T1,STOCK T2,STOCK_STATUS T3,MARKET T4 where T1.STOCKCDE = T2.STOCKCDE AND T1.STOCKCDE = T3.STOCKCDE AND T2.MARKETCDE = T4.MARKETCDE AND ORDERNO = '"
							+ orderId + "'");
			rs.next();

			sellbuy.setOrderID(rs.getString("ORDERNO"));
			// sellbuy.setAccountID (rs.getString("account_id"));
			sellbuy.setStockCode(rs.getString("STOCKCDE"));
			sellbuy.setStockName(rs.getString("MEIGARA_NAME_DBCS"));
			sellbuy.setOrderType(rs.getString("ORDER_TYPE"));
			sellbuy.setQuantity(rs.getInt("NUM_ORDER"));
			sellbuy.setOrderPrice(rs.getInt("ORDERPRICE"));

			sellbuy.setOrderDate(rs.getString("ORDER_DT"));
			sellbuy.setOrderStatus(rs.getString("STATUS"));
			sellbuy.setStockPrice(rs.getString("PRICE"));
			// sellbuy.setTransactiondate(rs.getString("transaction_date"));
			sellbuy.setMarket(rs.getString("MARKETNAME"));
			// sellbuy.setMarket(rs.getString("order_id"));
			// sellbuy.setOrderTimeout (rs.getString("order_"));
			sellbuy.setOrderCondition(rs.getString("CONDITIONS"));
			sellbuy.setSellBuy(rs.getString("SELL_BUY"));
			sellbuy.setBuyfee(rs.getString("BUYFEE"));
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

	/**
	 * 注文の作成
	 */
	public static String createOrder(OrdersellbuyDTO order,String number) {
		Connection con = null;
		PreparedStatement stmt = null;
		String orderId = "";

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			stmt = con.prepareStatement(
					"INSERT INTO ORDERS (ORDERNO,ACCTID, STOCKCDE, NUM_ORDER, ORDERPRICE, ORDER_TYPE, CONDITIONS, STATUS, SELL_BUY,BUYFEE) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?) ");


			orderId =  (String) new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());

			int num = Integer.parseInt(number);

			//初期化用
			//num = 999998;

			num = num + 1;

			if( num==1000000) {

				num =0 ;
			}

			System.out.printf(order.getStockCode());

			String num2 = String.format("%06d", num);

			String neworderId = orderId + num2;

			stmt.setString(1,neworderId);
			stmt.setString(2, order.getAccountId());
			stmt.setString(3, order.getStockCode());
			stmt.setInt(4, order.getQuantity());
			stmt.setDouble(5, order.getOrderPrice());
			stmt.setString(6, order.getOrderType());
			stmt.setString(7, order.getOrderCondition());
			stmt.setString(8, "注文中");
			stmt.setString(9, "買");
			stmt.setString(10, order.getBuyfee());

			// クエリの実行
			stmt.executeUpdate();

			return neworderId;

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
		return number;
}




	/**
	 * 注文一覧作成
	 */
	public static List<OrdersellbuyDTO> getOrder(String accountId) {
		ArrayList<OrdersellbuyDTO> list = new ArrayList<OrdersellbuyDTO>();
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"select T1.NUM_ORDER,T1.ORDERPRICE from ORDERS T1 where  T1.ACCTID = '"+ accountId+"'AND DATE(T1.ORDER_DT) = CURRENT_DATE");

			while (rs.next()) {
				OrdersellbuyDTO getOrder = new OrdersellbuyDTO();
				//getOrder.setOrderID(rs.getString("ORDERNO"));
				// sellbuy.setAccountID (rs.getString("account_id"));
				//getOrder.setStockCode(rs.getString("STOCKCDE"));
				//getOrder.setStockName(rs.getString("MEIGARA_NAME_DBCS"));
				//getOrder.setOrderType(rs.getString("ORDER_TYPE"));
				getOrder.setQuantity(rs.getInt("NUM_ORDER"));
				getOrder.setOrderPrice(rs.getInt("ORDERPRICE"));
				//getOrder.setOrderDate(rs.getString("ORDER_DT"));
				//getOrder.setOrderStatus(rs.getString("STATUS"));
				//getOrder.setStockPrice(rs.getString("PRICE"));
				// getOrder.setTransactiondate(rs.getString("transaction_date"));
				//getOrder.setMarket(rs.getString("MARKETNAME"));
				// getOrder.setMarket(rs.getString("order_id"));
				// getOrder.setOrderTimeout (rs.getString("order_"));
				//getOrder.setOrderCondition(rs.getString("CONDITION"));
				//getOrder.setSellBuy(rs.getString("SELL_BUY"));

				list.add(getOrder);
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
	 * 注文内容の取り消し
	 */
	public static boolean removeOrder(String orderId) {
		Connection con = null;
		PreparedStatement stmt = null;
	//	String orderId = "";

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/icwdb?autoReconnect=true&useSSL=false", "root", "root");
			stmt = con.prepareStatement(
					"DELETE FROM ORDERS WHERE orderno = ?");

			stmt.setString(1,orderId);

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


}
