<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>イベント情報登録完了</title>
</head>
<body>
	<h1>イベント情報登録完了</h1>
	<hr>
	<p>イベント情報の登録が完了しました。</p>
	<div>
	<table>
		<tr>
			<td>日にち:</td>
			<td>${ivent.date}</td>
		</tr>
		<tr>
			<td>イベント名:</td>
			<td>${ivent.iventName}</td>
		</tr>
		<tr>
			<td>メモ:</td>
			<td>${ivent.memo}</td>
		</tr>
	</table>
	</div>

    <form method="POST" action="/top">
     	<br/><input type = "submit" value="調整さんTOP" />
    </form>
</body>
</html>