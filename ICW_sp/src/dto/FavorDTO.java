package dto;

/**
 * DTOのサンプル
 */
public class FavorDTO {
	private String orderID;
	private String stockCode;
	private String stockName;
	private String orderType;
	private int quantity;
	private double orderPrice;
	private String orderDate;
	private String orderStatus;
	private String stockPrice;
	private String orderCommission;
	private String market;
	private String orderTimeout;
	private String orderCondition;
	private String accountId;
	private String sellBuy;
	private String favorite;
	
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String instrumentCode) {
		this.stockCode = instrumentCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String instrumentName) {
		this.stockName = instrumentName;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(String stockPrice) {
		this.stockPrice = stockPrice;
	}
	public String getOrderCommission() {
		return orderCommission;
	}
	public void setOrderCommission(String orderCommission) {
		this.orderCommission = orderCommission;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getOrderTimeout() {
		return orderTimeout;
	}
	public void setOrderTimeout(String orderTimeout) {
		this.orderTimeout = orderTimeout;
	}
	public String getOrderCondition() {
		return orderCondition;
	}
	public void setOrderCondition(String orderCondition) {
		this.orderCondition = orderCondition;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getSellBuy() {
		return sellBuy;
	}
	public void setSellBuy(String sellBuy) {
		this.sellBuy = sellBuy;
	}
	
	public String getFavorite() {
		return favorite;
	}
	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}
	
	
}
