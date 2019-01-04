<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dto.*" %>

<% List<StockDTO> instrumentList = (List<StockDTO>)request.getAttribute("instrumentList"); %>
<% String errorMessage = (String)request.getAttribute("errorMessage"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>銘柄検索</title>
</head>
<body>
	<%@ include file="_Nav.jsp"%>
	<div class="container">
		<h1>検索結果</h1>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>銘柄名</th>
					<th>銘柄コード</th>
					<th>市場</th>
					<th>業種</th>
					<th class="text-right">単元株</th>
					<th class="text-right">株価</th>
					<th class="text-right">始値</th>
					<th class="text-right">高値</th>
					<th class="text-right">安値</th>
					<th>買付注文</th>
				</tr>
			</thead>
			<%
			if (errorMessage != null) {
			%>
				<div class="alert alert-danger" role="alert"><%=errorMessage%></div>
			<%
			}
			else{
			%>
			<tbody>
				<!-- instrumentList内の要素を一つずつ処理 -->
				<%
					for (StockDTO instrument : instrumentList) {

				%>
				<tr>
					<th scope="row"><a
						href="StockDetail?code=<%=instrument.getStockCode()%>"><%=instrument.getStockName()%></a></th>
					<td><a
						href="StockDetail?code=<%=instrument.getStockCode()%>"><%=instrument.getStockCode()%></a></td>
					<td><%=instrument.getMarket()%></td>
					<td><%=instrument.getIndustry()%></td>
					<td class="text-right"><%=instrument.getUnit()%></td>
					<td class="text-right"><%=instrument.getStockValueNow()%></td>
					<td class="text-right"><%=instrument.getStockValueOpen()%></td>
					<td class="text-right"><%=instrument.getStockValueHigh()%></td>
					<td class="text-right"><%=instrument.getStockValueLow()%></td>
					<td>
						<p>
							<a href="BuyOrder?code=<%=instrument.getStockCode()%>" class="btn btn-default btn-sm">買付注文</a>
						</p>
					</td>
				</tr>
				<%
					}
					}
				%>

		</table>
	</div>
</body>
</html>