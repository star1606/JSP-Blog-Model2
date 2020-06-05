<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>
<!--  상대경로.. -->








<div class="container">

	<form action="/blog/user?cmd=joinProc" method="POST" class="was-validated" onsubmit="return validate()">
		<div class="form-group">
			<label for="username">Username:</label>
			<button type="button" class="btn btn-warning float-right" onclick="usernameCheck()">중복확인</button>
			<input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>

		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>


		<div class="form-group">
			<label for="email">Email:</label> <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>


		<div class="form-group">
			<label for="address">address:</label>
			<button type="button" class="btn btn-warning float-right" onclick="goPopup()">주소검색</button>
			<input type="text" class="form-control" id="address" placeholder="Enter address" name="address" required readonly>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>




		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</div>

<%-- 
<script>
	function goPopup() {
		var pop = window.open("/blog/juso/jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");
	}

	function jusoCallBack(roadFullAddr) {
		var tfAddress = document.querySelector("#address");
		tfAddress.value = roadFullAddr;
		// document.form.roadFullAddr.value = roadFullAddr;		
	}
</script>



<script>
	var isCheckedUsername = false;

	function validate() {
		if (!isCheckedUsername) {
			alert('username 중복체크를 해주세요');
		}
		return isCheckedUsername;
		alert()
	}

	function usernameCheck() {
		// 성공(ajax)
		var tfUsername = $('#username').val();
		//alert(tfUsername);
		console.log(tfUsername);
		$.ajax({
			type : 'get',
			url : '/blog/user?cmd=usernameCheck&username=' + tfUsername
		// 람다식에서의 JS
		}).done(function(result) { // 람다식을 쓰면 타입을 몰라도 쓸수 있다.
			console.log(result); // this 문제때문에 람다식씀 람다식쓰면
			if (result == 1) {
				alert('아이디가 중복되었습니다.');
			} else if (result == 0) { // === 3개는 타입까지 비교 == 값만비교
				alert('사용하실 수 있는 아이디입니다.');
				isCheckedUsername = true;

			} else {
				console.log('develop : 서버오류');

			}

		}).fail(function(error) {
			console.log(error); // 내가 생각하는 this를 쓸 수 있다.
			//원하는 곳의 this로갈수있다
		}); // JAVA에서의 람다식
		// 타입을 몰라도 된다.
		//성공하면 done으로 가고 실패하면 fail로 감				
		// play(()->){ });
		isCheckedUsername = true; //나두면 바로 때려짐
	}
</script>
--%>

<script	src="/blog/js/join.js"></script>

<%@ include file="../include/footer.jsp"%>