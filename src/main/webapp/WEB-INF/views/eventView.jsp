<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>イベント情報一覧</title>

<link rel="stylesheet" href="../../resources/css/sanitize.css">
<link rel="stylesheet" href="../../resources/css/style.css">

</head>
<body>
<div class="content">
	<h1>イベント参照</h1>
    <hr>

	<b>イベント名</b><br>
	${event.eventName}<br><br>
	<b>メモ</b><br>
	${event.memo}<br>
	<br>
	<b>日にち候補</b><br>
	${event.eventDate}<br>
	<br>

	<table border="1" cellpadding="5" cellspacing="0">
		<tr>
			<th>日程</th>
			<th>〇</th>
			<th>△</th>
			<th>×</th>
		</tr>

		<c:forEach var="answerAggregate" items="${answerAggregateList}">
			<tr>
				<td>${answerAggregate.eventDate}</td>
				<td>${answerAggregate.circleCnt}人</td>
				<td>${answerAggregate.triangleCnt}人</td>
				<td>${answerAggregate.crossCnt}人</td>
			</tr>
		</c:forEach>
	</table>

	<br>

	<p>出欠を入力する</p>
	<hr>
	<form method="POST" modelAttribute="answerFormList" action="/answer">
		<p><b>表示名</b></p>
		<input type="text" name="participantName"><br/>
		<br/>

		<p><b>日にち候補</b></p>

		<c:forEach var="answerForm" items="${answerFormList}">
			${answerForm.eventDate}
			<input type="radio" name="${answerForm.answer}" value="○">○
			<input type="radio" name="${answerForm.answer}" value="△" checked>△
			<input type="radio" name="${answerForm.answer}" value="×">×
		</c:forEach>

		<br>
		<p><b>コメント</b></p>
	<input type="text" name="answerComment"><br/>
	<br/>
	<button type="submit" disabled>入力する(すみません、実装させられませんでした)</button>
	</form>
	<br>
     <form method="POST" action="/top">
     	<button type="submit">調整さんTOP</button>
    </form>
</div>
</body>
</html>