<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dto.*" %>

<% List<OrdersellbuyDTO> orders = (List<OrdersellbuyDTO>)request.getAttribute("orders"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>注文一覧画面</title>
</head>


<body>
<%@ include file="_Nav.jsp" %>

<div class="container">
	<h1>注文一覧</h1>
	 <table class="table table-hover">
	   <thead>
        <tr>
          <th>受付番号</th>
          <th>銘柄</th>
          <th>銘柄CD</th>
          <th>注文方式</th>
          <th>注文数</th>
          <th>金額</th>
          <th>日時</th>
          <th>注文状態</th>
          <th>売買区分</th>
　        <th>取消画面へ</th>
          <th>取消実行</th>
        </tr>
      </thead>

	 <tbody>
		<!-- orders内の要素を一つずつ処理 -->
		<%
			for (OrdersellbuyDTO order : orders) {
		%>

		<tr>
			<td><a href="OrderDetail?id=<%=order.getOrderID()%>"><%=order.getOrderID()%></a></td>
			<td><a href="StockDetail?code=<%=order.getStockCode()%>"><%=order.getStockName()%></a></td>
			<td><a href="StockDetail?code=<%=order.getStockCode()%>"><%=order.getStockCode()%></a></td>
			<td><%=order.getOrderType()%></td>
			<td><%=order.getQuantity()%></td>
			<td><%=order.getOrderPrice()%></td>
			<td><%=order.getOrderDate()%></td>
			<td><%=order.getOrderStatus()%></td>
			<td><%=order.getSellBuy()%></td>
			<td><a href="OrderRemove?id=<%=order.getOrderID()%>"><button type="button"
						class="btn btn-default btn-sm">取消画面へ</button>
					</a></td>
			<td><a href="OrderRemove2?id=<%=order.getOrderID()%>"><button type="button"
						class="btn btn-default btn-sm">取消</button>
					</a></td>
		</tr>

		<%
			}
		%>
			</tbody>
	</table>
</div>
</body>
</html>


