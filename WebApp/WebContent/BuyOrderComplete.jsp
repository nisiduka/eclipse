<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String orderId = (String)request.getAttribute("orderId"); %>

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
	<div class="container">
		<h1>注文完了</h1>
		<p>注文を完了しました。</p>
		<p>
			受付番号: <a href="OrderDetail?id=<%=orderId%>"><%=orderId%></a>
		</p>
		<p id="buttons">
			<a href="Top" class="btn btn-default">トップページへ戻る</a>
		</p>
	</div>
</body>
</html>