<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>イベント情報登録完了</title>

<link rel="stylesheet" href="../../resources/css/sanitize.css">
<link rel="stylesheet" href="../../resources/css/style.css">

</head>
<body>
<div class="content">
	<h1>イベント情報登録完了</h1>
	<hr>
	<p>イベント情報の登録が完了しました。</p>

	<p>イベントID:
	<input type="text" value="${event.eventId}"><br>

	<p>イベント名:
	${event.eventName}<br>
	<p>日にち:
	${event.eventDate}<br>
	<p>メモ:
	${event.memo}<br>

     <form method="POST" action="/top">
     	<button type="submit">調整さんTOP</button>
    </form>
</div>
</body>
</html>