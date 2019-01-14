<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="dto.*" %>

 <% StockDTO detail = (StockDTO)request.getAttribute("detail"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style type="text/css">
#basic-info {
	font-size: 20px;
}

#kehai th {
	text-align: center;
}
</style>
<title>銘柄詳細</title>
</head>
<body>
	<%@ include file="_Nav.jsp"%>
	<div class="container">
		<div class="row">
			<h1>銘柄詳細</h1>
			<dl id="basic-info" class="dl-horizontal">
				<dt>銘柄名</dt>
				<dd><%=detail.getStockName()%></dd>
				<dt>銘柄コード</dt>
				<dd><%=detail.getStockCode()%></dd>
				<dt>市場</dt>
				<dd><%=detail.getMarket()%></dd>
			</dl>
		</div>

		<div class="row">
			<div class="col-md-4">
				<img src="images/chart.png" height="212px" width="350px" /> <br> <br>
				<br>
				<p class="text-center" style="font-size: 24px;">
					現在値<%=detail.getStockValueNow()%></p>
				<br> <br> <br>
				<div>
					<p class="text-center"><a href="BuyOrder?code=<%=detail.getStockCode()%>" class="btn btn-primary btn-lg">買付</a></p>
				</div>
				<br> <br> <br>
				<!-- / div.col-md-4 -->
			</div>
			<div class="col-md-4">
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th>売気配</th>
								<th>-円</th>
							</tr>
							<tr>
								<th>買気配</th>
								<th>-円</th>
							</tr>
							<tr>
								<th>始値</th>
								<th><%=detail.getStockValueOpen()%>円</th>
							</tr>
							<tr>
								<th>高値</th>
								<th><%=detail.getStockValueHigh()%>円</th>
							</tr>
							<tr>
								<th>安値</th>
								<th><%=detail.getStockValueLow()%>円</th>
							</tr>
							<tr>
								<th>年初来高値</th>
								<th><%=detail.getAnnualStockValueHigh()%>円</th>
							</tr>
							<tr>
								<th>年初来安値</th>
								<th><%=detail.getAnnualStockValueLow()%>円</th>
							</tr>
							<tr>
								<th>時価総額</th>
								<th><%=detail.getMarketCap()%>円</th>
							</tr>
							<tr>
								<th>発行済株式数</th>
								<th><%=detail.getIssuedStock()%>株</th>
							</tr>
							<tr>
								<th>出来高</th>
								<th><%=detail.getTodaysStock()%>株</th>
							</tr>
							<tr>
								<th>売買代金</th>
								<th><%=detail.getTodaysVolume()%>円</th>
							</tr>
						</thead>
					</table>
					<br> <br> <br>
					<table class="table">
						<thead>
							<tr>
								<th>単元株</th>
								<th><%=detail.getUnit()%>株</th>
							</tr>
							<tr>
								<th>業種</th>
								<th><%=detail.getIndustry()%></th>
							</tr>
							<tr>
								<th>決済期</th>
								<th>-</th>
							</tr>
						</thead>
					</table>
				</div>
				<!-- / div.col-md-4 -->
			</div>
			<div class="col-md-4">
				<div class="table-responsive">
					<table id="kehai" class="table">
						<thead>
							<tr>
								<th>売</th>
								<th>気配値</th>
								<th>買</th>
							</tr>
							<tr>
								<th></th>
								<th>成行</th>
								<th></th>
							</tr>
							<tr>
								<th></th>
								<th>OVER</th>
								<th></th>
							</tr>
							<tr>
								<th>-</th>
								<th>-</th>
								<th>-</th>
							</tr>
							<tr>
								<th>-</th>
								<th>-</th>
								<th>-</th>
							</tr>
							<tr>
								<th>-</th>
								<th>-</th>
								<th>-</th>
							</tr>
							<tr>
								<th>-</th>
								<th>-</th>
								<th>-</th>
							</tr>
							<tr>
								<th>-</th>
								<th>-</th>
								<th>-</th>
							</tr>
							<tr>
								<th>-</th>
								<th>-</th>
								<th>-</th>
							</tr>
							<tr>
								<th>-</th>
								<th>-</th>
								<th>-</th>
							</tr>
							<tr>
								<th>-</th>
								<th>-</th>
								<th>-</th>
							</tr>
							<tr>
								<th>-</th>
								<th>-</th>
								<th>-</th>
							</tr>
							<tr>
								<th>-</th>
								<th>-</th>
								<th>-</th>
							</tr>
							<tr>
								<th>-</th>
								<th>-</th>
								<th>-</th>
							</tr>
							<tr>
								<th>-</th>
								<th>-</th>
								<th>-</th>
							</tr>
							<tr>
								<th>-</th>
								<th>-</th>
								<th>-</th>
							</tr>
						</thead>
					</table>
				</div>
				<!-- / div.col-md-4 -->
			</div>
			<!-- / div.row -->
		</div>
		<!-- / div.container -->
	</div>
</body>
</html>