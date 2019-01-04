<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String stockcode = (String)request.getAttribute("stockcde"); %>

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
		<h1>お気に入り登録</h1>
		<p>お気に入り登録が完了しました</p>

		<p id="buttons">
			<a href="Top" class="btn btn-default">トップページに戻る</a>
		</p>
	</div>
</body>
</html>