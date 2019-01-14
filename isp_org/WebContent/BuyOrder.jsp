<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*" %>
<% String errorMessage = (String)request.getAttribute("errorMessage"); %>
<% StockDTO instrument = (StockDTO)request.getAttribute("instrument"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style type="text/css">
form {
	margin-top: 30px;
}

#buttons {
	margin-top: 30px
}
</style>
<title>買い注文入力画面</title>
</head>
<body>
	<%@ include file="_Nav.jsp"%>
	<div class="container">
		<h1>買い注文入力画面</h1>
		<%
			if (errorMessage != null) {
		%>
		<div class="alert alert-danger" role="alert"><%=errorMessage%></div>
		<%
			}
		%>
		<form action="BuyOrderConfirm" method="post" class="form-horizontal">
			<!-- 銘柄名 -->
			<div class="form-group">
				<label class="col-sm-2 control-label">銘柄名</label>
				<div class="col-sm-10">
					<p class="form-control-static"><%=instrument.getStockName()%></p>
				</div>
			</div>
			<!-- 銘柄コード -->
			<div class="form-group">
				<label class="col-sm-2 control-label">銘柄コード</label>
				<div class="col-sm-10">
					<p class="form-control-static"><%=instrument.getStockCode()%></p>
				</div>
				<input type="hidden" name="code"
					value="<%=instrument.getStockCode()%>">
			</div>
			<!-- 単位株数 -->
			<div class="form-group">
				<label class="col-sm-2 control-label">単位株数</label>
				<div class="col-sm-10">
					<p class="form-control-static"><%=instrument.getUnit()%></p>
				</div>
			</div>
			<!-- 注文数量 -->
			<div class="form-group">
				<label class="col-sm-2 control-label">注文数量</label>
				<div class="col-sm-3">
					<input type="number" name="quantity" class="form-control"
						placeholder="株">
				</div>
			</div>
			<!-- 注文方式 -->
			<div class="form-group">
				<label class="col-sm-2 control-label">注文方式</label>
				<div class="col-sm-10">
					<!-- 選択肢 -->
					<div class="radio">
						<label> <input type="radio" name="orderType" value="指値"
							checked>指値
						</label>
					</div>
					<div class="radio">
						<label> <input type="radio" name="orderType" value="成行">成行
						</label>
					</div>
					<!-- /選択肢 -->
				</div>
			</div>
			<!-- 注文価格 -->
			<div class="form-group">
				<label class="col-sm-2 control-label">注文価格</label>
				<div class="col-sm-3">
					<input type="number" name="price" class="form-control">
				</div>
			</div>
			<!-- 執行条件 -->
			<div class="form-group">
				<label class="col-sm-2 control-label">執行条件</label>
				<div class="col-sm-10">
					<!-- 選択肢 -->
					<div class="radio">
						<label> <input type="radio" name="condition" value="無条件"
							checked>無条件
						</label>
					</div>
					<div class="radio">
						<label> <input type="radio" name="condition" value="寄付">
							寄付
						</label>
					</div>
					<div class="radio">
						<label> <input type="radio" name="condition" value="引け">引け
						</label>
					</div>
					<div class="radio">
						<label> <input type="radio" name="condition" value="指成">
							指成
						</label>
					</div>
					<!-- /選択肢 -->
				</div>
			</div>
			<!-- ボタン -->
			<div class="form-group" id="buttons">
				<div class="col-sm-offset-2 col-sm-10">
					<a href="InstrumentDetail?code=<%=instrument.getStockCode()%>"
						class="btn btn-default">戻る</a>
					<button type="submit" class="btn btn-primary">確認画面へ</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>