<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style type="text/css">
#logout-box {
	width: 400px;
	margin: 100px auto 30px;
	text-align: center;
}

#logout-box .message-main {
	font-size: 36px;
}

#logout-box .message-sub {
	font-size: 24px;
}

#logout-box .button {
	margin-top: 50px;
}
</style>
<title>ログアウト画面</title>
</head>
<body>
	<div class="container">
		<div id="logout-box">
			<p class="message-main">ログアウトしました</p>
			<p class="message-sub">ご利用ありがとうございました</p>

			<p class="button">
				<a href="Login.jsp" class="btn btn-primary btn-lg active">再ログイン</a>
			</p>
		</div>
	</div>
</body>
</html>