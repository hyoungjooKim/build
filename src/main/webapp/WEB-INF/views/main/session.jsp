<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<center>
<br />
<h2>로그인이 확인되었습니다.</h2>
<br />
<h4> 해당 창은 3초 뒤 자동으로 닫습니다.</h4>
</center>
<script>
setTimeout('closed()',3000);
function closed() {
	self.close();
	}
</script>