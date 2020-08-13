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
		function checkValue() {
			if(!isChecked){
				alert('중복확인을 눌러주세요');
				return false;
			}
			
			var uiId = document.querySelector('#ui_id');
			if (uiId.value.trim().length<4) {
				alert('아이디 확인해 주세요');
				uiId.focus();
				return false;
			}
			var ui_pwd = document.querySelector('#ui_password');
			if (ui_pwd.value.trim().length <1||ui_pwd.value.trim().length > 20) {
				alert('비번확인점');
				ui_pwd.focus();
				return false;

			}
			var ui_name = document.querySelector('#ui_name');
			if (ui_name.value.trim().length<1 ||ui_name.value.trim().length>150) {
				alert('이름확인점');
				ui_name.focus();
				return false;

			}
			var ui_age = document.querySelector('#ui_age');
			if (ui_age.value.trim().length<1 ||ui_age.value.trim().length>150) {
				alert('나이확인점');
				ui_age.focus();
				return false;

			}
			var ui_birth = document.querySelector('#ui_birth');
			if (ui_birth.value.trim().length !=10) {
				alert('생년월일 확인점');
				ui_birth.focus();
				return false;

			}
			var ui_phone = document.querySelector('#ui_phone');
			if(!ui_phone.value){
				if (ui_phone.value.trim().length > 20) {
					alert('폰번확인점');
					ui_phone.focus();
					return false;
				}

			}
			var ui_email = document.querySelector('#ui_email');
			if(!ui_email.value){
				if (ui_email.value.trim().length > 100) {
					alert('이멜확인점');
					ui_email.focus();
					return false;
				}

			}
			var ui_nickname = document.querySelector('#ui_nickname');
			if (ui_nickname.value.trim().length > 20) {
				alert('닉넴확인점');
				ui_nickname.focus();
				return false;

			}
		}
		function checkId(){
			var id = document.querySelector('#ui_id').value;
			var xhr = new XMLHttpRequest();
			xhr.open('GET','/user/checkId?ui_id='+id);
			xhr.onreadystatechange = function(){
				if(xhr.readyState==4){
					if(xhr.status==200){
						alert(xhr.responseText);
					}
				}
			}
			xhr.send();
		}
	</script>
	<form action="/user/join" method="post" onsubmit="return checkValue()">
		
		ID:<input type="text" name="ui_id" id="ui_id">
		<button type="button" onclick = "checkId()">중복확인</button><br>
		<br> PWD:<input type="password" name="ui_password" id="ui_password"><br>
		이름:<input type="text" name="ui_name" id="ui_name"><br>
		age:<input type="number" name="ui_age" id="ui_age"><br>
		birth:<input type="date" name="ui_birth" id="ui_birth"><br>
		phone:<input type="text" name="ui_phone" id="ui_phone"><br>
		e-mail:<input type="text" name="ui_email" id="ui_email"><br>
		별명:<input type="text" name="ui_nickname" id="ui_nickname"><br>
		<button>회원가입</button>

	</form>
	
</body>
</html>