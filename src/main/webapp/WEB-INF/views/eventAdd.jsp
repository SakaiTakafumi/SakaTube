<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>イベント情報登録</title>

<link rel="stylesheet" href="../../resources/css/sanitize.css">
<link rel="stylesheet" href="../../resources/css/style.css">

</head>
<body>
<div class="content">
	<h1>イベント情報登録</h1>
	<h2>30秒でつくれる！カンタン出欠表</h2>
	<hr>
	<form method="post" action="/eventAddResult" name="eventInfo" onSubmit="return check()">
		<p><b>■STEP1 イベント名</b></p>
		<p>※今期もお疲れ様でした飲み会、打ち合わせなど</p>
		<input type="text" name="eventName"><br>
		<br>
		<p><b>■STEP2 候補日程</b></p>
		<p>※候補日程／日時を入力してください</p>
		<p>候補の区切りは改行で判断されます。</p>
		<p>例:</p>
		<p>　8/7(月) 20:00～</p>
		<p>　8/8(火) 20:00～</p>
		<p>　8/9(水) 20:00～</p>
		<textarea rows="10" placeholder="候補の区切りは改行で判断されます。" name="eventDate" cols="24"></textarea><br/>
		<br>
		<p><b>■メモ(任意)</b></p>
		<p>※飲み会の日程調整しましょう！出欠〆切は◯日</p>
		<textarea rows="10" name="memo" cols="24"></textarea><br>
		<br/>
		<p><button type="submit">出欠表を作る</button>
	</form>
	<br>
     <form method="POST" action="/top">
     	<button type="submit">調整さんTOP</button>
    </form>
</div>
</body>

<script type="text/javascript">
function check() {
	// イベント名の入力をチェック
	if(document.eventInfo.eventName.value == "") {
		window.alert('「STEP1 イベント名」は必須です。');
		return false;
	}
	// 候補日程の入力をチェック
	else if(document.eventInfo.eventDate.value == "") {
		window.alert('「STEP2 候補日程」は必須です。');
		return false;
	}
	return true;
}
</script>
</html>