<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style type="text/css">
#instruction {
	margin-top: 30px;
	font-size: 20px;
}
</style>
<title>トップ画面</title>
</head>
<body>
	<%@ include file="_Nav.jsp"%>
	<div class="container">
		<h1>長谷川ネット証券 オンライン・トレーディング・システム</h1>
		<dl class="dl-horizontal" id="instruction">
			<dt>銘柄検索</dt>
			<dd>画面上部の検索窓に銘柄名の一部か銘柄コードを入力してください</dd>
			<dt>注文入力</dt>
			<dd>銘柄一覧画面、あるいは銘柄詳細画面から注文画面に進むことができます</dd>
			<dt>注文一覧</dt>
			<dd>画面上部の「注文一覧」から、自分がこれまでに行った注文の履歴を確認できます</dd>
			<dt>ログアウト</dt>
			<dd>画面上部の「ログアウト」ボタンからログアウトできます</dd>
		</dl>
	</div>
</body>
</html>