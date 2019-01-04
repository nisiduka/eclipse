<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.*" %>
<% StockDTO instrument = (StockDTO)request.getAttribute("instrument"); %>
<% String quantity = (String)request.getAttribute("quantity"); %>
<% String orderType = (String)request.getAttribute("orderType"); %>
<% String price = (String)request.getAttribute("price"); %>
<% String condition = (String)request.getAttribute("condition"); %>
<% String Buyfee = (String)request.getAttribute("Buyfee"); %>


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
<title>注文内容確認画面</title>
</head>
<body>
	<%@ include file="_Nav.jsp"%>
	<div class="container">
		<h1>注文内容確認画面</h1>
		<p>次の内容で注文します。</p>
		<p>よろしければ「続行」ボタンを、修正する場合は「修正」ボタンをクリックしてください。</p>
		<form action="BuyOrderComplete" method="post" class="form-horizontal">
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
				<div class="col-sm-10">
					<p class="form-control-static"><%=quantity%>
						株
					</p>
				</div>
				<input type="hidden" name="quantity" value="<%=quantity%>">
			</div>
			<!-- 注文方式 -->
			<div class="form-group">
				<label class="col-sm-2 control-label">注文方式</label>
				<div class="col-sm-10">
					<p class="form-control-static"><%=orderType%></p>
				</div>
				<input type="hidden" name="orderType" value="<%=orderType%>">
			</div>
			<!-- 注文価格 -->
			<div class="form-group">
				<label class="col-sm-2 control-label">注文価格</label>
				<div class="col-sm-10">
					<p class="form-control-static"><%=price%>
						円
					</p>
				</div>
				<input type="hidden" name="price" value="<%=price%>">
			</div>
			<!-- 注文手数料 -->
			<div class="form-group">
				<label class="col-sm-2 control-label">注文手数料</label>
				<div class="col-sm-10">
					<p class="form-control-static"><%=Buyfee%>
						円
					</p>
				</div>
				<input type="hidden" name="buyfee" value="<%=Buyfee%>">
			</div>
			
			<!-- 執行条件 -->
			<div class="form-group">
				<label class="col-sm-2 control-label">執行条件</label>
				<div class="col-sm-10">
					<p class="form-control-static"><%=condition%></p>
				</div>
				<input type="hidden" name="condition" value="<%=condition%>">
			</div>
			<!-- ボタン -->
			<div class="form-group" id="buttons">
				<div class="col-sm-offset-2 col-sm-10">
					<a href="BuyOrder?code=<%=instrument.getStockCode()%>"
						class="btn btn-default">修正</a>
					<button type="submit" class="btn btn-primary">続行</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>