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
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 注文一覧
	 */
	public static List<OrdersellbuyDTO> getOrdersellbuyList(String accountId) {

		ArrayList<OrdersellbuyDTO> list = new ArrayList<OrdersellbuyDTO>();
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/icwdb", "postgres", "password");
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
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/icwdb", "postgres", "password");
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
	public static String createOrder(OrdersellbuyDTO order) {
		Connection con = null;
		PreparedStatement stmt = null;
		String orderId = "";

		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/icwdb", "postgres", "password");
			stmt = con.prepareStatement(
					"INSERT INTO ORDERS (ORDERNO,ACCTID, STOCKCDE, NUM_ORDER, ORDERPRICE, ORDER_TYPE, CONDITIONS, STATUS, SELL_BUY) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?) ");

			orderId =  new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
			stmt.setString(1,orderId);
			stmt.setString(2, order.getAccountId());
			stmt.setString(3, order.getStockCode());
			stmt.setInt(4, order.getQuantity());
			stmt.setDouble(5, order.getOrderPrice());
			stmt.setString(6, order.getOrderType());
			stmt.setString(7, order.getOrderCondition());
			stmt.setString(8, "注文中");
			stmt.setString(9, "買");

			// クエリの実行
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

		// 受付番号を返す
		return orderId;
	}

	/**
	 * 注文一覧作成
	 */
	public static List<OrdersellbuyDTO> getOrder(String accountId) {
		ArrayList<OrdersellbuyDTO> list = new ArrayList<OrdersellbuyDTO>();
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/icwdb", "postgres", "password");
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


}
