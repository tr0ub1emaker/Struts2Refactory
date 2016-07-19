<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>正在跳转</title>

<script type="text/javascript">
	function countDown(secs, surl) {  
		var jumpTo = document.getElementById('jumpTo');
		jumpTo.innerHTML = secs;
		if (--secs > 0) {
			setTimeout("countDown(" + secs + ",'" + surl + "')", 1000);
		} else {
			location.href = surl;
		}
	}
</script>

</head>
<body>
	登陆成功，请等待...<span id="jumpTo">3</span>
	<script type="text/javascript">
		countDown(5, 'http://www.baidu.com/');
	</script>
</body>
</html>