<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>イベント情報登録</title>
</head>
<body>
	<h1>イベント情報登録</h1>
	<hr>
	<p>イベント情報を入力し、送信ボタンをクリックしてください。</p>
	<form method="post" action="/iventAddResult">
		<table>
			<tr>
				<td>日にち:</td>
				<td><input type="text" name="date"></td>
			</tr>
			<tr>
				<td>イベント名:</td>
				<td><input type="text" name="iventName"></td>
			</tr>
			<tr>
				<td>メモ:</td>
				<td><input type="text" name="memo"></td>
			</tr>
		</table>
		<input type="submit" value="送信"><br/><br/>
	</form>

     <form method="POST" action="/top">
     	<br/><input type = "submit" value="調整さんTOP" />
    </form>
</body>
</html>