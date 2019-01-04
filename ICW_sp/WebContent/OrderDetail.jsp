<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%@ page import="dto.*" %>

 <% OrdersellbuyDTO detail = (OrdersellbuyDTO)request.getAttribute("detail"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>注文詳細</title>
</head>


<body>
<%@ include file="_Nav.jsp" %>

<div class="container">
	<h1>注文詳細</h1>

	<div class="row">
        <div class="col-md-6 col-md-offset-4">
    <table class="table table-hover">


       <tr>  <th>受付番号</th><td><%=detail.getOrderID()%></td> </tr>
       <tr>   <th>約定状態</th><td><%=detail.getOrderStatus()%></td> </tr>
       <tr>   <th>銘柄</th><td><%=detail.getStockName()%></td> </tr>
       <tr>   <th>銘柄CD</th><td><%=detail.getStockCode()%></td> </tr>
       <tr>   <th>単価</th><td><%=detail.getOrderPrice()%>円</td> </tr>
       <tr>   <th>数量</th><td><%=detail.getQuantity()%>株</td> </tr>
       <tr>   <th>約定金額</th><td><%=detail.getOrderPrice() * detail.getQuantity()%>円</td> </tr>
       <tr>   <th>約定数量</th><td><%=detail.getQuantity()%></td> </tr>
       <tr>   <th>手数料</th><td><%=detail.getBuyfee()%></td> </tr>
       <tr>   <th>売買区分</th><td><%=detail.getOrderType()%></td></tr>
       <tr>     <th>市場</th><td><%=detail.getMarket()%></td> </tr>
       <tr>   <th>執行条件</th><td><%=detail.getOrderCondition()%></td></tr>
　     <tr>     <th>注文受付日</th><td><%=detail.getOrderDate()%></td> </tr>
  <!-- <tr>   <th>注文有効期限</th><td><%=detail.getOrderTimeout()%></td> </tr> --> 


      </table>


	<thead>
        <tr>
　        <th><a href="OrderRemove2?id=<%=detail.getOrderID()%>"><button type="button" class="btn btn-default btn-sm">取消</button></th>
          <th><a href="OrderList"><button type="button" class="btn btn-default btn-sm">一覧に戻る</button></th>
        </tr>
      </thead>

</div>
</div>
</div>
</body>
</html>


