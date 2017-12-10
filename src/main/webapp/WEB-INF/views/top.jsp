<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>調整さん</title>
</head>
<body>
	<h1>調整さん</h1>
	<hr>
	<form method="POST" action="/iventAdd">
    	<br><input type = "submit" value="イベント情報登録" />
    </form>

	<form method="POST" action="/iventView">
    	<br><input type = "submit" value="イベント情報参照" />
    </form>

    <form method="POST" action="/answer">
    	<br><input type = "submit" value="出欠回答" />
    </form>
    <br>
</body>
</html>