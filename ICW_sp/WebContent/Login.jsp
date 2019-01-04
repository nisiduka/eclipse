<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String errorMessage = (String)request.getAttribute("errorMessage"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style type="text/css">
#subtitle {
	margin-top: 50px;
	font-size: 20px;
	text-align: center;
}

h1 {
	margin-top: 10px;
	margin-bottom: 40px;
	text-align: center;
}

#login-box {
	width: 400px;
	margin: 0 auto;
}

#login-box .button-box {
	margin-top: 50px;
	text-align: center;
}
</style>
<title>ログイン画面</title>
</head>
<body>
	<div class="container">
		<p id="subtitle">長谷川ネット証券 オンライン・トレーディング・システム</p>
		<h1>ログイン画面</h1>
		<div id="login-box">
			<% if (errorMessage != null) { %>
			<div class="alert alert-danger" role="alert"><%=errorMessage%></div>
			<% } %>
			<form action="Login1" method="post" class="form-horizontal">
				<div class="form-group">
					<label for="ID" class="col-sm-3 control-label">ログインID</label>
					<div class="col-sm-9">
						<input type="text" name="id" class="form-control" id="ID"
							placeholder="ログインID">
					</div>
				</div>
				<div class="form-group">
					<label for="Password" class="col-sm-3 control-label">パスワード</label>
					<div class="col-sm-9">
						<input type="password" name="password" class="form-control"
							id="Password" placeholder="パスワード">
					</div>
				</div>

				<br>
				
				<div class="button-box">
					<button type="submit" class="btn btn-primary btn-lg">ログイン</button>
				</div>
			</form>
				
				<br>	
				
			<div class="button-box">
				<a href="LoginNew.jsp">新規登録会員用</a>
				
			</div>
			
			
		</div>
	</div>
</body>
</html>