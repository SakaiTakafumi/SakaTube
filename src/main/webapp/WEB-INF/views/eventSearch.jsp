<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>イベント検索</title>

<link rel="stylesheet" href="../../resources/css/sanitize.css">
<link rel="stylesheet" href="../../resources/css/style.css">

</head>
<body>
<div class="content">
	<h1>イベント検索</h1>
	<hr>
	<p>イベントIDを入力し、検索ボタンをクリックしてください。</p>

	<form method="post" action="/eventView" name="eventIdForm" onSubmit="return check()">
		<p><b>■イベントID</b></p>
		<input type="text" name="eventId"><br><br>
		<button type="submit">イベントを検索する</button>
	</form>
	<br>
     <form method="POST" action="/top">
     	<button type="submit">調整さんTOP</button>
    </form>
</div>
</body>

<script type="text/javascript">
function check() {
	// イベントIDの入力をチェック
	if(document.eventIdForm.eventId.value == "") {
		window.alert('「イベントID」を入力して下さい。');
		return false;
	}
	return true;
}
</script>
</html>