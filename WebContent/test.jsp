<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
/* 0,'',null,undefined, false == false
	!0,!'',!null,!undefined,!false == true
*/

<%
String name = request.getParameter("name");
%>
<%=name%>

function func(str){
	if(str){
		alert(str.length);
	}else{
		alert('파라메터 없음 ')
	}
}
</script>
</body>
</html>