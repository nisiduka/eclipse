<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dto.*" %>


<% List<FavorDTO> favor = (List<FavorDTO>)request.getAttribute("favor"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>お気に入り一覧</title>
</head>


<body>
<%@ include file="_Nav.jsp" %>

<div class="container">
	<h1>お気に入り一覧</h1>
	 <table class="table table-hover">
	   <thead>
        <tr>
          <th>お気に入り番号</th>
          <th>銘柄コード</th>
          <th>お気に入り取り消し</th>

        </tr>
      </thead>

	 <tbody>
		<!-- orders内の要素を一つずつ処理 -->
		<%
			for (FavorDTO favors : favor) {
		%>

		<tr>
			<td><%=favors.getFavorite()%></td>
			<td><%=favors.getStockCode()%></td>
			<td><a href="FavorRemove?favorite=<%=favors.getFavorite()%>"><button type="button"
						class="btn btn-default btn-sm">取消</button>
					</a></td>
		</tr>
		<%
			}
		%>
			</tbody>
	</table>
</div>
</body>
</html>


