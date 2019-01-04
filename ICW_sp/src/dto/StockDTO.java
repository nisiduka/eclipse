package dto;


/**
 * DTOのサンプル
 */
public class StockDTO {
	private String stockName;
	private String stockCode;
	private String market;
	private String industry;
	private int unit;
	private double stockValueNow;
	private double stockValueOpen;
	private double stockValueHigh;
	private double stockValueLow;
	private String msg;

	private String assetField;
	private String fiscalPeriod;
	private long issuedStock;
	private long marketCap ;
	private long todaysStock;
	private long todaysVolume;
	private String annualHighPriceDate;
	private String annualLowPriceDate;
	private double annualStockValueHigh;
	private double annualStockValueLow;


	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public double getStockValueNow() {
		return stockValueNow;
	}
	public void setStockValueNow(double stockValueNow) {
		this.stockValueNow = stockValueNow;
	}
	public double getStockValueOpen() {
		return stockValueOpen;
	}
	public void setStockValueOpen(double stockValueOpen) {
		this.stockValueOpen = stockValueOpen;
	}
	public double getStockValueHigh() {
		return stockValueHigh;
	}
	public void setStockValueHigh(double stockValueHigh) {
		this.stockValueHigh = stockValueHigh;
	}
	public double getStockValueLow() {
		return stockValueLow;
	}
	public void setStockValueLow(double stockValueLow) {
		this.stockValueLow = stockValueLow;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getAssetField() {
		return assetField;
	}
	public void setAssetField(String assetField) {
		this.assetField = assetField;
	}
	public String getFiscalPeriod() {
		return fiscalPeriod;
	}
	public void setFiscalPeriod(String fiscalPeriod) {
		this.fiscalPeriod = fiscalPeriod;
	}
	public long getMarketCap() {
		marketCap = (long) (issuedStock * stockValueNow);
		return marketCap;
	}
	/*public void setMarketCap() {
		this.marketCap = issuedStock * stockValueNow;
	}*/
	public long getIssuedStock() {
		return issuedStock;
	}
	public void setIssuedStock(long issuedStock) {
		this.issuedStock = issuedStock;
	}
	public long getTodaysStock() {
		return todaysStock;
	}
	public void setTodaysStock(long todaysStock) {
		this.todaysStock = todaysStock;
	}
	public long getTodaysVolume() {
		return todaysVolume;
	}
	public void setTodaysVolume(long todaysVolume) {
		this.todaysVolume = todaysVolume;
	}
	public String getAnnualHighPriceDate() {
		return annualHighPriceDate;
	}
	public void setAnnualHighPriceDate(String annualHighPriceDate) {
		this.annualHighPriceDate = annualHighPriceDate;
	}
	public String getAnnualLowPriceDate() {
		return annualLowPriceDate;
	}
	public void setAnnualLowPriceDate(String annualLowPriceDate) {
		this.annualLowPriceDate = annualLowPriceDate;
	}
	public double getAnnualStockValueHigh() {
		return annualStockValueHigh;
	}
	public void setAnnualStockValueHigh(double annualStockValueHigh) {
		this.annualStockValueHigh = annualStockValueHigh;
	}
	public double getAnnualStockValueLow() {
		return annualStockValueLow;
	}
	public void setAnnualStockValueLow(double annualStockValueLow) {
		this.annualStockValueLow = annualStockValueLow;
	}

}