<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>イベント情報一覧</title>
</head>
<body>
	<h1>イベント情報一覧</h1>
    <hr>
	<div>
	<table>
		<tr>
			<th>日にち</th>
			<th>イベント名</th>
			<th>備考</th>
			<th>登録日時</th>
			<th>更新日時</th>
		</tr>
		<tr>
			 <td>${ivent.date}</td>
			 <td>${ivent.iventName}</td>
			 <td>${ivent.memo}</td>
			 <td>${ivent.insertDate}</td>
			 <td>${ivent.updateDate}</td>
		</tr>
	</table>
	</div>
</body>
</html>