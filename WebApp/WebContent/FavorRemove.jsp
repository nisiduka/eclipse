<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- DTOのjavaファイルを読み込むにはもちろんimportが必要-->

<%@ page import="dto.*" %>

<% String stockcode = (String)request.getAttribute("stockcde"); %>

<% OrdersellbuyDTO favoriteStock = (OrdersellbuyDTO) session.getAttribute("favoriteStock"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style type="text/css">
#buttons {
	margin-top: 30px
}
</style>
<title>注文完了</title>
</head>
<body>


	<%@ include file="_Nav.jsp"%>
	<center>
	<div class="container">
		<h1>お気に入り削除</h1>
		<p>お気に入り削除が完了しました。</p>
		<p>
			株銘柄 : <%=favoriteStock.getStockName()%>
		</p>
		<p id="buttons">
			<a href="Top" class="btn btn-default">トップページに戻る</a>
		</p>
	</div>
	</center>
</body>
</html>