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
<title>注文取り消し画面</title>
</head>


<body>
<%@ include file="_Nav.jsp" %>

<div class="container">
	<h1>予約内容取り消しを行いました</h1>
</div>


</body>
</html>


