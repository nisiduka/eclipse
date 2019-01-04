package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.StockDTO;

/**
 * 銘柄を扱うDAO
 */
public class StockDAO {

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 銘柄検索
	 */
	public static List<StockDTO> getStockList(String name) {

		List<StockDTO> list = new ArrayList<StockDTO>();
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/icwdb", "postgres", "password");
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select T1.*,T2.MARKETNAME,T3.INDUSTRY_NAME,T4.PRICE,T4.FIRSTPRICE,T4.HIGHPRICE,T4.LOWPRICE,T4.VOLUME from STOCK T1 LEFT OUTER JOIN MARKET T2 ON T1.MARKETCDE = T2.MARKETCDE LEFT OUTER JOIN INDUSTRY T3 ON T1.INDUSTRY=T3.INDUSTRYCDE LEFT OUTER JOIN STOCK_STATUS T4 ON T1.STOCKCDE = T4.STOCKCDE where T1.MEIGARA_NAME_DBCS LIKE '%" + name + "%'");

			while (rs.next()) {
				StockDTO meigara = new StockDTO();
				meigara.setStockName(rs.getString("MEIGARA_NAME_DBCS"));
				meigara.setStockCode(rs.getString("STOCKCDE"));
				meigara.setMarket(rs.getString("MARKETNAME"));
				meigara.setUnit(rs.getInt("UNIT"));
				meigara.setIndustry(rs.getString("INDUSTRY_NAME"));
				//meigara.setFiscalPeriod(rs.getString("FISCAL_PERIOD"));
				meigara.setStockValueNow(rs.getDouble("PRICE"));
				meigara.setStockValueOpen(rs.getDouble("FIRSTPRICE"));
				meigara.setStockValueHigh(rs.getDouble("HIGHPRICE"));
				meigara.setStockValueLow(rs.getDouble("LOWPRICE"));
				meigara.setAnnualStockValueHigh(rs.getDouble("MOST_HIGHPRICE"));
				meigara.setAnnualStockValueLow(rs.getDouble("MOST_LOWPRICE"));
				meigara.setAnnualHighPriceDate(rs.getString("MOST_HIGHPRICE_DT"));
				meigara.setAnnualLowPriceDate(rs.getString("MOST_LOWPRICE_DT"));
				meigara.setIssuedStock(rs.getLong("TOTALNUM_STOCK"));
				meigara.setTodaysVolume(rs.getLong("VOLUME"));

				list.add(meigara);
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
	 * 銘柄コード検索
	 */
	public static List<StockDTO> getStockListc(String name) {

		List<StockDTO> list = new ArrayList<StockDTO>();
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/icwdb", "postgres", "password");
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select T1.*,T2.MARKETNAME,T3.INDUSTRY_NAME,T4.PRICE,T4.FIRSTPRICE,T4.HIGHPRICE,T4.LOWPRICE,T4.VOLUME from STOCK T1 LEFT OUTER JOIN MARKET T2 ON T1.MARKETCDE = T2.MARKETCDE LEFT OUTER JOIN INDUSTRY T3 ON T1.INDUSTRY=T3.INDUSTRYCDE LEFT OUTER JOIN STOCK_STATUS T4 ON T1.STOCKCDE = T4.STOCKCDE where T1.STOCKCDE = '" + name+"'");

			while (rs.next()) {
				StockDTO meigara = new StockDTO();
				meigara.setStockName(rs.getString("MEIGARA_NAME_DBCS"));
				meigara.setStockCode(rs.getString("STOCKCDE"));
				meigara.setMarket(rs.getString("MARKETNAME"));
				meigara.setUnit(rs.getInt("UNIT"));
				meigara.setIndustry(rs.getString("INDUSTRY_NAME"));
				//meigara.setFiscalPeriod(rs.getString("FISCAL_PERIOD"));
				meigara.setStockValueNow(rs.getDouble("PRICE"));
				meigara.setStockValueOpen(rs.getDouble("FIRSTPRICE"));
				meigara.setStockValueHigh(rs.getDouble("HIGHPRICE"));
				meigara.setStockValueLow(rs.getDouble("LOWPRICE"));
				meigara.setAnnualStockValueHigh(rs.getDouble("MOST_HIGHPRICE"));
				meigara.setAnnualStockValueLow(rs.getDouble("MOST_LOWPRICE"));
				meigara.setAnnualHighPriceDate(rs.getString("MOST_HIGHPRICE_DT"));
				meigara.setAnnualLowPriceDate(rs.getString("MOST_LOWPRICE_DT"));
				meigara.setIssuedStock(rs.getLong("TOTALNUM_STOCK"));
				meigara.setTodaysVolume(rs.getLong("VOLUME"));

				list.add(meigara);
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
	 * 銘柄検索
	 */
	public static StockDTO getStock(String code) {

		Connection con = null;
		Statement stmt = null;
		StockDTO detail = new StockDTO();

		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/icwdb", "postgres", "password");
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select T1.*,T2.MARKETNAME,T3.INDUSTRY_NAME,T4.PRICE,T4.FIRSTPRICE,T4.HIGHPRICE,T4.LOWPRICE,T4.VOLUME from STOCK T1 LEFT OUTER JOIN MARKET T2 ON T1.MARKETCDE = T2.MARKETCDE LEFT OUTER JOIN INDUSTRY T3 ON T1.INDUSTRY=T3.INDUSTRYCDE LEFT OUTER JOIN STOCK_STATUS T4 ON T1.STOCKCDE = T4.STOCKCDE where T1.STOCKCDE = '" + code + "'");
			rs.next();

			detail.setStockCode(rs.getString("STOCKCDE"));
			detail.setStockName(rs.getString("MEIGARA_NAME_DBCS"));
			detail.setMarket(rs.getString("MARKETNAME"));
			detail.setStockValueNow(rs.getDouble("PRICE"));
			detail.setStockValueOpen(rs.getDouble("FIRSTPRICE"));
			detail.setStockValueHigh(rs.getDouble("HIGHPRICE"));
			detail.setStockValueLow(rs.getDouble("LOWPRICE"));
			detail.setAnnualHighPriceDate(rs.getString("MOST_HIGHPRICE_DT"));
			detail.setAnnualStockValueHigh(rs.getDouble("MOST_HIGHPRICE"));
			detail.setAnnualLowPriceDate(rs.getString("MOST_LOWPRICE_DT"));
			detail.setAnnualStockValueLow(rs.getDouble("MOST_LOWPRICE"));
			detail.setIssuedStock(rs.getLong("TOTALNUM_STOCK"));
			//detail.setTodaysStock(rs.getLong("TODAYS_STOCK"));
			detail.setTodaysVolume(rs.getLong("VOLUME"));
			detail.setUnit(rs.getInt("UNIT"));
			detail.setIndustry(rs.getString("INDUSTRY_NAME"));
			//detail.setMarketCap();
			//detail.setFiscalPeriod(rs.getString("FISCAL_PERIOD"));
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

		return detail;
	}
}
