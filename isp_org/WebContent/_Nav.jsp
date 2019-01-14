<%@ page language="java" pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="Top">長谷川ネット証券</a>
		</div>
		<form action="StockSearch" method="post"
			class="navbar-form navbar-left">
			<div class="form-group">
				<input type="text" name="keyword" class="form-control" placeholder="銘柄名、銘柄コード">
			</div>
			<button type="submit" class="btn btn-default">検索</button>
		</form>
		<ul class="nav navbar-nav">
			<li><a href="OrderList">注文一覧</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="Logout">ログアウト</a></li>
		</ul>
	</div>
</nav>
